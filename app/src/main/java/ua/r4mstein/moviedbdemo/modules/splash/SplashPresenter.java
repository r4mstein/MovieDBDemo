package ua.r4mstein.moviedbdemo.modules.splash;

import android.content.Context;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.providers.AccountProvider;
import ua.r4mstein.moviedbdemo.modules.base.ActivityView;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.login.activity.LoginActivity;
import ua.r4mstein.moviedbdemo.modules.main.MainActivity;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.NetworkManager;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class SplashPresenter extends BaseActivityPresenter<SplashPresenter.SplashView> {

    private AccountProvider mAccountProvider = new AccountProvider();

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        if (NetworkManager.isOnline(getView().getSplashContext())) {
            executeWithoutProgress(Observable.timer(1, TimeUnit.SECONDS), aLong -> chooseNextScreen());
        } else {
            NetworkManager.getInfoDialogWithFinish(getRouter());
        }
    }

    private void chooseNextScreen() {
        if (SharedPrefManager.getInstance().retrieveRequestToken().isEmpty() ||
                SharedPrefManager.getInstance().retrieveSessionId().isEmpty())
            goToLoginScreen();
        else getAccountDetails();
    }

    private void goToLoginScreen() {
        int flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
        getRouter().startActivity(LoginActivity.class, flags, null);
        getRouter().finishActivity();
    }

    private void getAccountDetails() {
        executeWithoutProgress(mAccountProvider.getAccountDetails(API_KEY, SharedPrefManager.getInstance().retrieveSessionId()),
                userModel -> {
                    if (SharedPrefManager.getInstance().getUser() == null) SharedPrefManager.getInstance().saveUser(userModel);
                    goMainScreen();
                },
                throwable -> {
                    Logger.d(throwable.getMessage());
                });
    }

    private void goMainScreen() {
        int flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
        getRouter().startActivity(MainActivity.class, flags, null);
        getRouter().finishActivity();
    }

    interface SplashView extends ActivityView {

        Context getSplashContext();
    }
}
