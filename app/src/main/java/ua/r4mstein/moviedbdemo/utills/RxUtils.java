package ua.r4mstein.moviedbdemo.utills;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class RxUtils {

    public static void unsubscribeIfNotNull(CompositeDisposable _subscription) {
        if (_subscription != null) {
            _subscription.dispose();
        }
    }

    public static CompositeDisposable getNewCompositeSubIfUnsubscribed(CompositeDisposable _subscription) {
        if (_subscription == null || _subscription.isDisposed()) {
            return new CompositeDisposable();
        }

        return _subscription;
    }

    public static void click(View _view, Consumer _action) {
        RxView.clicks(_view)
                .throttleFirst(800, TimeUnit.MILLISECONDS)
                .subscribe(_action, Logger::e);
    }


}
