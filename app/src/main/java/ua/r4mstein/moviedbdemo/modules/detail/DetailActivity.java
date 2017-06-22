package ua.r4mstein.moviedbdemo.modules.detail;

import android.support.v7.widget.Toolbar;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.BaseActivity;

public class DetailActivity extends BaseActivity<DetailPresenter> {

    private Toolbar mToolbar;

    @Override
    protected DetailPresenter initPresenter() {
        return new DetailPresenter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail;
    }

    @Override
    public int getFragmentContainer() {
        return R.id.flFrameCont_AD;
    }

    @Override
    protected void findUI() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar_AD);
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
