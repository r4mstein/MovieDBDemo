package ua.r4mstein.moviedbdemo.modules.login;

import android.content.Intent;
import android.text.TextUtils;

import io.reactivex.annotations.NonNull;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.LoginApi;
import ua.r4mstein.moviedbdemo.data.models.response.RequestTokenModel;
import ua.r4mstein.moviedbdemo.data.providers.LoginProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.modules.main.MainActivity;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class SignInPresenter extends BaseFragmentPresenter<SignInPresenter.SignInView> {

    private LoginApi mLoginApi = new LoginProvider();

    void btnLoginClicked() {
        if (isValidData(getView().getUserName(), getView().getPass())) getRequestToken();
    }

    private void getRequestToken() {
        executeWithoutProgress(mLoginApi.getRequestToken(API_KEY),
                requestTokenModel -> {
                    String requestToken = requestTokenModel.getRequestToken();
                    validateRequestToken(requestToken);

                    Logger.d("RAM: RequestToken = " + requestToken);
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    private void validateRequestToken(String requestToken) {
        execute(mLoginApi.validateRequestToken(API_KEY, getView().getUserName(), getView().getPass(),
                requestToken),
                requestTokenModel -> {
                    getSessionId(requestTokenModel);

                    Logger.d("RAM: RequestToken = " + requestTokenModel.getRequestToken());
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    private void getSessionId(@NonNull RequestTokenModel requestTokenModel) {
        execute(mLoginApi.createSessionId(API_KEY, requestTokenModel.getRequestToken()),
                sessionModel -> {
                    SharedPrefManager.getInstance().saveSessionId(sessionModel.getSessionId());
                    Logger.d("RAM: SessionId = " + sessionModel.getSessionId());

                    goToMainScreen();
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    private void goToMainScreen() {
        int flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
        getRouter().startActivity(MainActivity.class, flags, null);
        getRouter().finishActivity();
    }

    private boolean isValidData(String userName, String pass) {

        getView().errorUserName(false, "");
        if (TextUtils.isEmpty(userName)) {
            getView().errorUserName(true, "User name is empty");
            return false;
        }

        getView().errorPassword(false, "");
        if (TextUtils.isEmpty(pass)) {
            getView().errorPassword(true, "Password is empty");
            return false;
        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().hideToolbar();
    }

    interface SignInView extends FragmentView {

        void hideToolbar();
        String getUserName();
        String getPass();
        void errorUserName(boolean errorState, String msg);
        void errorPassword(boolean errorState, String msg);
    }
}
