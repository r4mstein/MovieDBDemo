package ua.r4mstein.moviedbdemo.modules.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import ua.r4mstein.moviedbdemo.modules.dialog.QuestionDialog;

public interface Router {

    void init(ActivityView activity, BaseActivityPresenter activityPresenter);

    void onBackPressed();

    void clearBackStack();

    void replaceFragment(Fragment _fragment, boolean _addToBackStack);

    void replaceFragment(Fragment _fragment, boolean _addToBackStack, View _sharedElement, String _sharedName);

    void showInfoDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener);
//
//    void showErrorDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener);
//
//    void showErrorDialog(@StringRes int _title, String _message, View.OnClickListener _listener);
//
//    void showBlockDialog(@StringRes int _title, String _message, View.OnClickListener _listener);
//
//    void showEmailDialog(@StringRes int _title, String _message, View.OnClickListener _listener);

    void showDialog(BaseDialog _dialog, @StringRes int _title, @StringRes int _message, View.OnClickListener _positiveListener, View.OnClickListener _negativeListener);

    void showDialog(BaseDialog _dialog, @StringRes int _title, String _message, View.OnClickListener _listener, View.OnClickListener _negativeListener);

    void showDialog(BaseDialog _dialog, @StringRes int _title, String _message, @DrawableRes int _icon, View.OnClickListener _listener);
//
//    void showDialog(BaseDialog _dialog);

    void showQuestionDialog(QuestionDialog _dialog, @StringRes int _title, String _message, View.OnClickListener _positiveListener, View.OnClickListener _negativeListener);

    void showLoadingDialog();

    void hideLoadingDialog();

    void hideLoadDialog();

    void startActivity(Intent intent);

    void startActivity(Class _activityClass, int flags, Bundle _bundle, View... sharedViews);

    void startActivityForResult(Class _activityClass, int flags, Bundle _bundle, int requestCode, View... sharedViews);

    void startActivityFromFragment(Fragment _fragment, Class _activityClass, int flags, Bundle _bundle, int requestCode, View... sharedViews);

    void finishActivity();

    void hideKeyboard();

    void onSessionExpired();

    void setToolBarTitle(String title);

    boolean isInStack(Fragment fragment);

}
