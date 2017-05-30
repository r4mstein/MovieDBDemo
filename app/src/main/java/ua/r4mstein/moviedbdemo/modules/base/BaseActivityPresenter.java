package ua.r4mstein.moviedbdemo.modules.base;

import android.os.Bundle;

import ua.r4mstein.moviedbdemo.utills.Logger;


public abstract class BaseActivityPresenter<V extends ActivityView> extends BasePresenterImpl<V> {

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        Logger.logStackTrace();
        mRouter = new RouterImpl();
        mRouter.init(mView, this);
    }

}
