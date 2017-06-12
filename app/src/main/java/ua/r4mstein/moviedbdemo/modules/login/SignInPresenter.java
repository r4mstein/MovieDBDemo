package ua.r4mstein.moviedbdemo.modules.login;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.LoginApi;
import ua.r4mstein.moviedbdemo.data.models.response.RequestTokenModel;
import ua.r4mstein.moviedbdemo.data.providers.LoginProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Constants;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class SignInPresenter extends BaseFragmentPresenter<SignInPresenter.SignInView> {

    LoginApi mLoginApi = new LoginProvider();

    private String mRequestToken;

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        getRequestToken();
    }

    private void getRequestToken() {
        executeWithoutProgress(mLoginApi.getRequestToken(API_KEY),
                requestTokenModel -> {
                    mRequestToken = requestTokenModel.getRequestToken();
                    Logger.d("RAM: RequestToken = " + mRequestToken);
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void btnLoginClicked() {
        execute(mLoginApi.validateRequestToken(API_KEY, getView().getUserName(), getView().getPass(),
                mRequestToken),
                new Consumer<RequestTokenModel>() {
                    @Override
                    public void accept(@NonNull RequestTokenModel requestTokenModel) throws Exception {
                        Logger.d("RAM: RequestToken = " + requestTokenModel.getRequestToken());
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
