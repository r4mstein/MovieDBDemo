package ua.r4mstein.moviedbdemo.modules.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver;

import ua.r4mstein.moviedbdemo.utills.Logger;


public abstract class BaseActivity<P extends BaseActivityPresenter> extends AppCompatActivity
        implements ActivityView {

    private P mPresenter;
    private MenuController mMenuController;
    private boolean resumed;

    protected abstract P initPresenter();

    protected abstract void findUI();

    protected abstract int getLayoutResource();

    protected abstract void setupUI();

    protected MenuController initMenuController() {
        return null;
    }

    protected MenuController getMenuController() {
        return mMenuController;
    }

    @Override
    public boolean isActivityResumed() {
        return resumed;
    }

    @Override
    public int getFragmentContainer() {
        return 0;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutResource() != 0)
            setContentView(getLayoutResource());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(true);

        findUI();
        setupUI();

        if (mPresenter == null) {
            mPresenter = initPresenter();
        }
        mPresenter.bindView(this);
        mPresenter.onCreateView(savedInstanceState);

        getWindow().getDecorView().getRootView().getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getWindow().getDecorView().getRootView().getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                        mPresenter.onViewCreated();
                    }
                });
        if (mMenuController == null)
            mMenuController = initMenuController();
    }

    @Override
    public Object getLastCustomNonConfigurationInstance() {
        return super.getLastCustomNonConfigurationInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mMenuController != null) {
            mMenuController.onCreateOptionsMenu(menu, getMenuInflater());
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = initPresenter();
        }
        mPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroyView();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumed = true;
        getPresenter().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        resumed = false;
        getPresenter().onPause();
    }

    @Override
    public void onBackPressed() {
        mPresenter.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setToolbarTitle(String text) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(text);
        }
    }

    @Override
    public void setToolbarTitle(@StringRes int textRes) {

        if (getSupportActionBar() != null && textRes != 0) {
            getSupportActionBar().setTitle(textRes);
        }
    }

    @Override
    public void finishActivity() {
        supportFinishAfterTransition();
    }

    @Override
    public Point getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Logger.logStackTrace();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public Bundle getViewArguments() {
        return getIntent().getExtras();
    }

    @Override
    public AppCompatActivity asActivity() {
        return this;
    }

    protected final <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }

}
