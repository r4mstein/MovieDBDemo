package ua.r4mstein.moviedbdemo.modules.login;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.LoginApi;
import ua.r4mstein.moviedbdemo.data.models.response.RequestTokenModel;
import ua.r4mstein.moviedbdemo.data.models.response.SessionModel;
import ua.r4mstein.moviedbdemo.data.providers.LoginProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class SignInPresenter extends BaseFragmentPresenter<SignInPresenter.SignInView> {

    private LoginApi mLoginApi = new LoginProvider();

    @Override
    public void onViewCreated() {
        super.onViewCreated();


    }

    void btnLoginClicked() {
        getRequestToken();
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
                new Consumer<SessionModel>() {
                    @Override
                    public void accept(@NonNull SessionModel sessionModel) throws Exception {
                        Logger.d("RAM: SessionId = " + sessionModel.getSessionId());
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Logger.d(throwable.getMessage());
                    }
                });
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
    }
}
