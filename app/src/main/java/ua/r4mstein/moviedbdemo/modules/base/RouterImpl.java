package ua.r4mstein.moviedbdemo.modules.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.List;

import ua.r4mstein.moviedbdemo.modules.dialog.QuestionDialog;
import ua.r4mstein.moviedbdemo.utills.KeyboardManager;
import ua.r4mstein.moviedbdemo.utills.Logger;

public class RouterImpl implements Router {

    private ActivityView mActivity;
    private LoadingDialog progressDialog;
    private BaseDialog mDialog;


    @Override
    public void init(ActivityView activity, BaseActivityPresenter activityPresenter) {
        mActivity = activity;
        mActivity.asActivity().getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            BaseFragment fragment = (BaseFragment) mActivity.asActivity().getSupportFragmentManager()
                    .findFragmentById(mActivity.getFragmentContainer());
            if(fragment.getTitle() != 0) {
                mActivity.setToolbarTitle(fragment.getTitle());
            }
            if (fragment.getStringTitle() != null) {
                mActivity.setToolbarTitle(fragment.getStringTitle());
            }

        });

    }

    @Override
    public void onSessionExpired() {
//        showErrorDialog(R.string.error_network, R.string.error_session_expired, view -> {
//            startActivity(LoginActivity.class, 0, null);
//            finishActivity();
//        });
    }

    @Override
    public void clearBackStack() {
        mActivity.asActivity().getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackPressed() {
        if (mActivity.asActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            mActivity.asActivity().getSupportFragmentManager().popBackStack();
        } else
            mActivity.finishActivity();

    }

    @Override
    public boolean isInStack(Fragment _fragment) {
        if (mActivity.asActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            List<Fragment> fragmentList = mActivity.asActivity().getSupportFragmentManager().getFragments();
                for (Fragment fragment : fragmentList) {
                    if (fragment.getClass().getSimpleName().equals(_fragment.getClass().getSimpleName()))
                        return true;
                }
        }
        return false;
    }

    @Override
    public void replaceFragment(Fragment _fragment, boolean _addToBackStack) {
        replaceFragment(_fragment, _addToBackStack, null, "");
    }

    @Override
    public void replaceFragment(Fragment _fragment, boolean _addToBackStack, View _sharedElement, String _sharedName) {
        hideKeyboard();
        if (mActivity.getFragmentContainer() == 0) {
            new UnsupportedOperationException("There are not container for fragment" + getClass().getName());
        } else {
            FragmentTransaction fragmentTransaction = mActivity.asActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right/*, android.R.anim.slide_in_left, android.R.anim.slide_out_right*/);
            if (_addToBackStack) {
                fragmentTransaction.addToBackStack(null);
            }

            Fragment fragment = mActivity.asActivity().getSupportFragmentManager().findFragmentById(mActivity.getFragmentContainer());
            if (fragment != null) fragmentTransaction.hide(fragment);

            if (mActivity.asActivity().getSupportFragmentManager().getBackStackEntryCount() > 0 || _addToBackStack) {
                fragmentTransaction.add(mActivity.getFragmentContainer(), _fragment, _fragment.getClass().getName());
            } else {
                fragmentTransaction.replace(mActivity.getFragmentContainer(), _fragment, _fragment.getClass().getName());
            }
            fragmentTransaction.show(_fragment);

            if (_sharedElement != null)
                fragmentTransaction.addSharedElement(_sharedElement, _sharedName);

            fragmentTransaction.commitAllowingStateLoss();
            mActivity.setToolbarTitle(((BaseFragment) _fragment).getTitle());
        }

    }

    @Override
    public void startActivity(Intent intent) {
        mActivity.asActivity().startActivity(intent);
    }

    @Override
    public void startActivityForResult(Class _activityClass, int flags, Bundle _bundle, int requestCode, View... sharedViews) {
        Intent intent = new Intent(mActivity.asActivity(), _activityClass);
        if (flags != 0) intent.setFlags(flags);
        if (_bundle != null) intent.putExtras(_bundle);
        mActivity.asActivity().startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityFromFragment(Fragment _fragment, Class _activityClass, int flags, Bundle _bundle, int requestCode, View... sharedViews) {
        Intent intent = new Intent(mActivity.asActivity(), _activityClass);
        if (flags != 0) intent.setFlags(flags);
        if (_bundle != null) intent.putExtras(_bundle);
        mActivity.asActivity().startActivityFromFragment(_fragment, intent, requestCode);
    }

    @Override
    public void startActivity(Class _activityClass, int flags, Bundle _bundle, View... sharedViews) {
        Intent intent = new Intent(mActivity.asActivity(), _activityClass);
        if (flags != 0) intent.setFlags(flags);
        if (_bundle != null) intent.putExtras(_bundle);
        mActivity.asActivity().startActivity(intent);
        mActivity.asActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


    @Override
    public void finishActivity() {
        mActivity.finishActivity();
        mActivity.asActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void hideKeyboard() {
        KeyboardManager.hideKeyboard(mActivity.asActivity());
    }


    @Override
    public void setToolBarTitle(String title) {
         mActivity.setToolbarTitle(title);
    }

//    @Override
//    public void showInfoDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener) {
//        showDialog(new InfoDialog(), _title, _message, _listener, null);
//    }
//
//    @Override
//    public void showErrorDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener) {
////        showDialog(new ErrorDialog(), _title, _message, _listener, null);
//    }
//
//    @Override
//    public void showErrorDialog(@StringRes int _title, String _message, View.OnClickListener _listener) {
////        showDialog(new ErrorDialog(), _title, _message, _listener, null);
//    }
//
//    @Override
//    public void showBlockDialog(int _title, String _message, View.OnClickListener _listener) {
////        showDialog(new ErrorInfoDialog(), _title, _message, R.drawable.ic_not_interested, _listener);
//    }
//
//    @Override
//    public void showEmailDialog(@StringRes int _title, String _message, View.OnClickListener _listener) {
////        showDialog(new ErrorInfoDialog(), _title, _message, R.drawable.ic_chats, _listener);
//    }
//
//    @Override
//    public void showDialog(BaseDialog _dialog, @StringRes int _title, @StringRes int _message, View.OnClickListener _positiveListener, View.OnClickListener _negativeListener) {
//        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
//        if (mDialog != null && mDialog.isVisible()) mDialog.dismiss();
//        mDialog = _dialog;
//        mDialog.setTitle(_title);
//        mDialog.setMessage(_message);
//        mDialog.setOnPositiveClickListener(_positiveListener);
//        mDialog.setOnNegativeClickListener(_negativeListener);
//        mDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");
//
//    }
//
    @Override
    public void showDialog(BaseDialog _dialog, @StringRes int _title, String _message, View.OnClickListener _listener, View.OnClickListener _negativeListener) {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
        if (mDialog != null && mDialog.isVisible()) mDialog.dismiss();
        mDialog = _dialog;
        mDialog.setTitle(_title);
        mDialog.setMessage(_message);
        mDialog.setOnPositiveClickListener(_listener);
        mDialog.setOnNegativeClickListener(_negativeListener);
        mDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");
    }

    @Override
    public void showDialog(BaseDialog _dialog, @StringRes int _title, String _message, @DrawableRes int _icon, View.OnClickListener _listener) {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
        if (mDialog != null && mDialog.isVisible()) mDialog.dismiss();
        mDialog = _dialog;
        mDialog.setTitle(_title);
        mDialog.setMessage(_message);
        mDialog.setIcon(_icon);
        mDialog.setOnPositiveClickListener(_listener);
        mDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");
    }

    @Override
    public void showQuestionDialog(QuestionDialog _dialog, @StringRes int _title, String _message, View.OnClickListener _positiveListener, View.OnClickListener _negativeListener) {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
        if (mDialog != null && mDialog.isVisible()) mDialog.dismiss();
        mDialog = _dialog;
        mDialog.setTitle(_title);
        mDialog.setMessage(_message);
        mDialog.setOnPositiveClickListener(_positiveListener);
        mDialog.setOnNegativeClickListener(_negativeListener);
        mDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");
    }

    //
//    @Override
//    public void showDialog(BaseDialog _dialog) {
//        if (mDialog != null && mDialog.isVisible()) mDialog.dismiss();
//        mDialog = _dialog;
//        mDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");
//    }
//
    @Override
    public void showLoadingDialog() {
        if (mActivity.isActivityResumed()) {
            if (progressDialog == null) {
                progressDialog = new LoadingDialog();
            }

            if(progressDialog.isAdded()) {
                mActivity.asActivity().getSupportFragmentManager()
                        .beginTransaction().remove(progressDialog).commit();
            }

            if (!progressDialog.isShowing() /*&& App.getInstance().isForeground()*/)
                progressDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");
        }
    }

    @Override
    public void hideLoadingDialog() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            try {
                if (progressDialog != null && progressDialog.isShowing() && !progressDialog.isDismiss())
                    progressDialog.dismiss();
            } catch (IllegalStateException e) {
                Logger.e(e);
            }
        }, 400);
    }

    public void hideLoadDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing() && !progressDialog.isDismiss())
                progressDialog.dismiss();
        } catch (IllegalStateException e) {
            Logger.e(e);
        }
    }
}
