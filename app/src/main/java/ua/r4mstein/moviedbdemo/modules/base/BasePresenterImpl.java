package ua.r4mstein.moviedbdemo.modules.base;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;
import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.ErrorModel;
import ua.r4mstein.moviedbdemo.modules.dialog.InfoDialog;
import ua.r4mstein.moviedbdemo.utills.NetworkManager;
import ua.r4mstein.moviedbdemo.utills.RxUtils;


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
        if (NetworkManager.isOnline(getView().getViewContext())) {
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
        } else {
            NetworkManager.getInfoDialog(getRouter());
        }
        return null;
    }

    protected <T> Disposable execute(Observable<T> request, Consumer<T> onSuccess, Consumer<Throwable> onError, Action onComplete) {
        if (NetworkManager.isOnline(getView().getViewContext())) {
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
        } else {
            NetworkManager.getInfoDialog(getRouter());
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

    private <T> void onExecuteSuccess(T t, Consumer<T> onSuccess) throws Exception {
        if (onSuccess != null)
            onSuccess.accept(t);
    }

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

}
