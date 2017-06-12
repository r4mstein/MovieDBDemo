package ua.r4mstein.moviedbdemo.modules.splash;

import android.content.Intent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.modules.base.ActivityView;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.login.activity.LoginActivity;

public class SplashPresenter extends BaseActivityPresenter<SplashPresenter.SplashView> {

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        executeWithoutProgress(Observable.timer(1, TimeUnit.SECONDS), aLong -> goToLoginScreen());
    }

    private void goToLoginScreen() {
        int flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
        getRouter().startActivity(LoginActivity.class, flags, null);
        getRouter().finishActivity();
    }

    interface SplashView extends ActivityView {

    }
}
