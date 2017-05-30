package ua.r4mstein.moviedbdemo.modules.base;

import android.os.Bundle;

interface BasePresenter<V extends BaseView> {

    Router getRouter();

    void setRouter(Router router);

    void bindView(V _view);

    V getView();

    void onCreateView(Bundle savedInstanceState);

    void onDestroyView();

    void onPause();

    void onResume();

    void onStop();

    void onStart();

    void onViewCreated();

    void onBackPressed();

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);

}
