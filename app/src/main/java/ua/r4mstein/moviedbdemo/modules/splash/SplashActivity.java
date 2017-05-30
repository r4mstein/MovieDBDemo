package ua.r4mstein.moviedbdemo.modules.splash;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivity;

public class SplashActivity extends BaseActivity<SplashPresenter> {

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void findUI() {

    }

    @Override
    protected void setupUI() {

    }
}
