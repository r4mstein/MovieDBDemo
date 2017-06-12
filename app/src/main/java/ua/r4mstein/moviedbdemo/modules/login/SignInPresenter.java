package ua.r4mstein.moviedbdemo.modules.login;

import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;

public class SignInPresenter extends BaseFragmentPresenter<SignInPresenter.SignInView> {

    @Override
    public void onResume() {
        super.onResume();
        getView().hideToolbar();
    }

    interface SignInView extends FragmentView {

        void hideToolbar();
    }
}
