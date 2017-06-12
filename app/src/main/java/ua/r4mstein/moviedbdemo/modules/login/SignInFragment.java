package ua.r4mstein.moviedbdemo.modules.login;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;

public class SignInFragment extends BaseFragment<SignInPresenter>
        implements SignInPresenter.SignInView {

    @Override
    protected int getTitle() {
        return R.string.title_login;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected SignInPresenter initPresenter() {
        return new SignInPresenter();
    }

    @Override
    protected void findUI(View rootView) {

    }

    @Override
    protected void setupUI() {

    }

    @Override
    public void hideToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}
