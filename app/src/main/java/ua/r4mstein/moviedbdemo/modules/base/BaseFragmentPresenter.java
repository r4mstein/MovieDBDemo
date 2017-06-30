package ua.r4mstein.moviedbdemo.modules.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.api.base.HttpManager;
import ua.r4mstein.moviedbdemo.data.models.request.AddToWatchlistSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.MarkFavoriteSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.RateMovieSendModel;
import ua.r4mstein.moviedbdemo.data.providers.AccountProvider;
import ua.r4mstein.moviedbdemo.data.providers.MoviesProvider;
import ua.r4mstein.moviedbdemo.modules.detail.DetailActivity;
import ua.r4mstein.moviedbdemo.modules.dialog.ChooseActionDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.DialogRating;
import ua.r4mstein.moviedbdemo.modules.dialog.InfoDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.QuestionDialog;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.DELETE_RATING;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.FAVORITE_WATCHLIST;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.SET_RATING;
import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;
import static ua.r4mstein.moviedbdemo.utills.Constants.DETAILS_MOVIE_FRAGMENT;
import static ua.r4mstein.moviedbdemo.utills.Constants.DETAILS_PERSON_FRAGMENT;

public abstract class BaseFragmentPresenter<V extends FragmentView> extends BasePresenterImpl<V> {

    private MoviesProvider mMoviesProvider = new MoviesProvider();
    private AccountProvider mAccountProvider = new AccountProvider();

    public void getMovieAccountState(long movieId, String reasonType) {
        getRouter().showLoadingDialog();
        OkHttpClient client = new OkHttpClient();
        HttpManager httpManager = new HttpManager();

        client.newCall(httpManager.getRequestInstance(movieId)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.d(e.getMessage());
                getRouter().hideLoadDialog();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                JSONObject object;
                try {
                    String result = response.body().string();
                    Logger.d(result);
                    object = new JSONObject(result);

                    switch (reasonType) {
                        case FAVORITE_WATCHLIST:
                            boolean isFavorite = object.getBoolean("favorite");
                            boolean isWatchlist = object.getBoolean("watchlist");
                            getRouter().hideLoadDialog();
                            createDialog(movieId, isFavorite, isWatchlist);
                            break;
                        case SET_RATING:
                            double vote = 0;
                            if (!(object.getString("rated").equals("false"))) {
                                JSONObject objectRated = new JSONObject(object.getString("rated"));
                                vote = objectRated.getDouble("value");
                                Logger.d(String.valueOf(vote));
                            }
                            getRouter().hideLoadDialog();
                            createRatingDialog(movieId, vote);
                            break;
                        case DELETE_RATING:
                            boolean isRated = true;
                            if (object.getString("rated").equals("false"))
                                isRated = false;
                            getRouter().hideLoadDialog();
                            showDeleteRatingDialog(movieId, isRated);
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void createDialog(long movieId, boolean isFavorite, boolean isWatchlist) {
        FragmentManager manager = getView().getFragmManager();

        int addFavorite = View.GONE;
        int removeFavorite = View.VISIBLE;
        if (!isFavorite) {
            addFavorite = View.VISIBLE;
            removeFavorite = View.GONE;
        }

        int addWatchlist = View.GONE;
        int removeWatchlist = View.VISIBLE;
        if (!isWatchlist) {
            addWatchlist = View.VISIBLE;
            removeWatchlist = View.GONE;
        }

        ChooseActionDialog dialog = ChooseActionDialog.newInstance(addFavorite, addWatchlist, removeFavorite, removeWatchlist);
        dialog.setChooseActionClickListener(getView().getChooseActionClickListener(movieId, dialog));
        dialog.show(manager, "ChooseActionDialog");
    }

    protected void createRatingDialog(long movieId, double value) {
        FragmentManager manager = getView().getFragmManager();

        DialogRating dialogRating = DialogRating.newInstance((float) value);
        dialogRating.setDialogRatingClickListener(getView().getDialogRatingClickListener(movieId, dialogRating));
        dialogRating.show(manager, "DialogRating");
    }

    protected void showDeleteRatingDialog(long movieId, boolean isRated) {
        if (isRated) {
            getRouter().showQuestionDialog(new QuestionDialog(), R.string.app_name,
                    getView().getAppResources().getString(R.string.dialog_delete_rating_message),
                    v -> {
                        Logger.d("positive clicked");
                        deleteRatingOfMovie(movieId);
                    },
                    v -> Logger.d("negative clicked"));
        } else {
            getRouter().showDialog(new InfoDialog(), R.string.app_name,
                    getView().getAppResources().getString(R.string.dialog_delete_rating_attention_message),
                    v -> {
                    }, null);
        }
    }

    protected void deleteRatingOfMovie(long movieId) {
        execute(mMoviesProvider.deleteRating(movieId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId()),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> {
                        }, null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void markAsFavorite(long movieId, ChooseActionDialog dialog, boolean choice) {
        MarkFavoriteSendModel sendModel = new MarkFavoriteSendModel();
        sendModel.setFavorite(choice);
        sendModel.setMediaId(movieId);
        sendModel.setMediaType("movie");

        execute(mAccountProvider.markAsFavorite(SharedPrefManager.getInstance().getUser().getId(),
                API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> dialog.dismiss(), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void addToWatchlist(long movieId, ChooseActionDialog dialog, boolean choice) {
        AddToWatchlistSendModel sendModel = new AddToWatchlistSendModel();
        sendModel.setMediaType("movie");
        sendModel.setMediaId(movieId);
        sendModel.setWatchlist(choice);

        execute(mAccountProvider.addToWatchList(SharedPrefManager.getInstance().getUser().getId(),
                API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> dialog.dismiss(), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void rateMovie(long movieId, DialogRating dialog, float rating) {
        RateMovieSendModel sendModel = new RateMovieSendModel();
        sendModel.setValue(rating);

        execute(mMoviesProvider.rateMovie(movieId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> dialog.dismiss(), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void goToDetailScreen(long movieId) {
        Bundle bundle = new Bundle();
        bundle.putLong(DETAILS_MOVIE_FRAGMENT, movieId);
        getRouter().startActivity(DetailActivity.class, 0, bundle);
    }

    public void goToPersonDetailScreen(long personId) {
        Bundle bundle = new Bundle();
        bundle.putLong(DETAILS_PERSON_FRAGMENT, personId);
        getRouter().startActivity(DetailActivity.class, 0, bundle);
    }

}
