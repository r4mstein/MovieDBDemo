package ua.r4mstein.moviedbdemo.modules.login.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivity;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginPresenter.LoginView {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportFragmentManager().addOnBackStackChangedListener(() ->
                getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() > 0)
        );
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void findUI() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_AL);
    }

    @Override
    protected void setupUI() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public FragmentManager getAppFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public int getFragmentContainer() {
        return R.id.fl_container_AL;
    }
}
