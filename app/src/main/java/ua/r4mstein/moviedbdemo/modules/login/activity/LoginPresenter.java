package ua.r4mstein.moviedbdemo.modules.login.activity;

import ua.r4mstein.moviedbdemo.modules.base.ActivityView;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.login.SignInFragment;

public class LoginPresenter extends BaseActivityPresenter<LoginPresenter.LoginView> {

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        getRouter().replaceFragment(new SignInFragment(), false);
    }

    interface LoginView extends ActivityView {

    }
}
