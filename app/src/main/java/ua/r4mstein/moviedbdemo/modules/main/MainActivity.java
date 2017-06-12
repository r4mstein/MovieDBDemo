package ua.r4mstein.moviedbdemo.modules.main;

import android.support.v7.widget.Toolbar;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> {

    private Toolbar mToolbar;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public int getFragmentContainer() {
        return R.id.flFrameCont_AM;
    }

    @Override
    protected void findUI() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
    }

    @Override
    protected void setupUI() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportFragmentManager().addOnBackStackChangedListener(() ->
                getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() > 0)
        );
    }
}
