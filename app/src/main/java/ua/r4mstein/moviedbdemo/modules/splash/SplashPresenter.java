package ua.r4mstein.moviedbdemo.modules.splash;

import android.content.Intent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.modules.base.ActivityView;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.login.activity.LoginActivity;
import ua.r4mstein.moviedbdemo.modules.main.MainActivity;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

public class SplashPresenter extends BaseActivityPresenter<SplashPresenter.SplashView> {

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        executeWithoutProgress(Observable.timer(1, TimeUnit.SECONDS), aLong -> chooseNextScreen());
    }

    private void chooseNextScreen() {
        if (SharedPrefManager.getInstance().retrieveRequestToken().isEmpty() ||
                SharedPrefManager.getInstance().retrieveSessionId().isEmpty())
            goToLoginScreen();
        else goMainScreen();
    }

    private void goToLoginScreen() {
        int flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
        getRouter().startActivity(LoginActivity.class, flags, null);
        getRouter().finishActivity();
    }

    private void goMainScreen() {
        int flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
        getRouter().startActivity(MainActivity.class, flags, null);
        getRouter().finishActivity();
    }

    interface SplashView extends ActivityView {

    }
}
