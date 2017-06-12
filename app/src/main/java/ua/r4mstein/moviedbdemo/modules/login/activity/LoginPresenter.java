package ua.r4mstein.moviedbdemo.modules.login.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.ActivityView;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.login.SignInFragment;

public class LoginPresenter extends BaseActivityPresenter<LoginPresenter.LoginView> {

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        getRouter().replaceFragment(new SignInFragment(), false);
    }

    private void startFragment() {
        FragmentManager fragmentManager = getView().getAppFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        SignInFragment signInFragment = new SignInFragment();
        transaction.replace(R.id.fl_container_AL, signInFragment);
        transaction.commit();
    }

    interface LoginView extends ActivityView {
        FragmentManager getAppFragmentManager();
    }
}
