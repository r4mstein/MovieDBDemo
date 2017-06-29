package ua.r4mstein.moviedbdemo.modules.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import retrofit2.HttpException;
import retrofit2.Response;
import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.api.base.HttpManager;
import ua.r4mstein.moviedbdemo.data.models.response.ErrorModel;
import ua.r4mstein.moviedbdemo.modules.dialog.ChooseActionDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.DialogRating;
import ua.r4mstein.moviedbdemo.modules.dialog.InfoDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.QuestionDialog;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.RxUtils;

import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.DELETE_RATING;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.FAVORITE_WATCHLIST;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.SET_RATING;


public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    protected V mView;
    protected Router mRouter;
    protected CompositeDisposable mSubscriptions;

    @Override
    public Router getRouter() {
        return mRouter;
    }

    @Override
    public void setRouter(Router router) {
        mRouter = router;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onDestroyView() {
        RxUtils.unsubscribeIfNotNull(mSubscriptions);
    }

    protected <T> Disposable executeWithoutProgress(Observable<T> request, Consumer<T> onSuccess, Consumer<Throwable> onError) {
        return executeWithoutProgress(request, onSuccess, onError, null);
    }

    protected <T> Disposable executeWithoutProgress(Observable<T> request, Consumer<T> onSuccess) {
        return executeWithoutProgress(request, onSuccess, null, null);
    }

    protected <T> Disposable executeWithoutProgress(Observable<T> request) {
        return executeWithoutProgress(request, null, null, null);
    }

    protected <T> Disposable executeWithoutProgress(Observable<T> request, Consumer<T> onSuccess, Consumer<Throwable> onError, Action onComplete) {
        if (request != null) {
            Disposable subscription = request
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            t -> onExecuteSuccess(t, onSuccess),
                            throwable -> {
                                if (onError != null) {
                                    onError.accept(throwable);
                                    handleError(throwable);
                                }
                            }, () -> {
                                if (onComplete != null)
                                    onComplete.run();
                                }
                    );
            addSubscription(subscription);
            return subscription;
        }
        return null;
    }

    protected <T> Disposable execute(Observable<T> request, Consumer<T> onSuccess, Consumer<Throwable> onError, Action onComplete) {
        if (onComplete == null)
            getRouter().showLoadingDialog();
        if (request != null) {
            Disposable subscription = request
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            t -> {
                                hideLoading();
                                onExecuteSuccess(t, onSuccess);
                            },
                            throwable -> {
                                hideLoading();

                                if (onError != null) {
                                    onError.accept(throwable);
                                    handleError(throwable);
                                }
                            }, () -> onExecutingComplete(onComplete)

                    );
            addSubscription(subscription);
            return subscription;
        }
        return null;
    }

    private void handleError(Throwable throwable) throws IOException {
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;

            Response response = exception.response();
            String msg = "Unknown error";

                String jsonString = response.errorBody().string();

                ErrorModel error = new Gson().fromJson(jsonString, ErrorModel.class);
                if (error.getStatusMessage() != null)
                    msg = error.getStatusMessage();

            getRouter().showDialog(new InfoDialog(), R.string.error_title,
                    msg, v -> {
                    }, null);

        }
    }

    private void hideLoading() {
        getRouter().hideLoadingDialog();
    }

    protected <T> Disposable execute(Observable<T> request, Consumer<T> onSuccess, Consumer<Throwable> onError) {
        return execute(request, onSuccess, onError, null);
    }

    protected <T> Disposable execute(Observable<T> request, Consumer<T> onSuccess) {
        return execute(request, onSuccess, null, null);
    }

    protected <T> Disposable execute(Observable<T> request) {
        return execute(request, null, null, null);
    }

//
    private <T> void onExecuteSuccess(T t, Consumer<T> onSuccess) throws Exception {
        if (onSuccess != null)
            onSuccess.accept(t);
    }
//
//    protected void onExecutingError(Throwable throwable, Action1<Throwable> errorCallBack) {
//
//        if (!ErrorHandler.isSessionExpired(throwable)) {
//            if (errorCallBack != null) {
//                getRouter().hideLoadingDialog();
//                if (throwable.getCause() instanceof UnknownHostException) {
//                    getRouter().showErrorDialog(R.string.error_network, R.string.error_bad_network_connection, null);
//                    Logger.e(throwable);
//                } else {
//                    errorCallBack.call(throwable);
//                    Logger.e(throwable);
//                }
//            } else {
//                getRouter().hideLoadingDialog();
//                ErrorHandler.onError(getRouter(), throwable);
//            }
//        } else {
//                ErrorHandler.onLoginError(getRouter(), throwable);
//        }
//    }

    private void onExecutingComplete(Action onComplete) throws Exception {
        if (onComplete == null) {
            getRouter().hideLoadingDialog();
        } else {
            onComplete.run();
        }
    }


    @Override
    public void bindView(V _view) {
        mView = _view;
    }

    @Override
    public V getView() {
        return mView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void addSubscription(Disposable _subscription) {
        if (mSubscriptions == null)
            mSubscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(mSubscriptions);
        mSubscriptions.remove(_subscription);
        mSubscriptions.add(_subscription);
    }

    protected Bundle getArguments() {
        return getView().getViewArguments();
    }

    @Override
    public void onBackPressed() {
        mRouter.onBackPressed();
    }

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

    }

}
