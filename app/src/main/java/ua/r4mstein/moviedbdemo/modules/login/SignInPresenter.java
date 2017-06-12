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

public class SignInPresenter extends BaseFragmentPresenter<SignInPresenter.SignInView> {

    LoginApi mLoginApi = new LoginProvider();

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        getRequestToken();
    }

    private void getRequestToken() {
        execute(mLoginApi.getRequestToken(Constants.API_KEY),
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
    }
}
