package ua.r4mstein.moviedbdemo.modules.dialog;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.BaseDialog;
import ua.r4mstein.moviedbdemo.utills.RxUtils;

public class QuestionDialog extends BaseDialog {

    protected TextView tvTitle;
    protected TextView tvMessage;
    protected TextView btnClose;
    protected TextView btnOk;
    protected ImageView ivIcon;

    @StringRes
    private int mMessageRes;
    @StringRes
    private int mTitleRes;
    private String mMessage;
    @DrawableRes
    private int icon;

    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_question;
    }

    @Override
    protected void setupViews(View _rootView) {
        setCancelable(false);
        tvTitle = (TextView) _rootView.findViewById(R.id.tvTitle);
        tvMessage = (TextView) _rootView.findViewById(R.id.tvMessage);
        btnClose = (TextView) _rootView.findViewById(R.id.btnNegative);
        btnOk = (TextView) _rootView.findViewById(R.id.btnPositive);

        RxUtils.click(btnOk, new Consumer() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                QuestionDialog.this.onPositiveClick();
            }
        });

        RxUtils.click(btnClose, new Consumer() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                QuestionDialog.this.onNegativeClick();
            }
        });

        if (mMessageRes != 0) tvMessage.setText(getString(mMessageRes));
        else if (!TextUtils.isEmpty(mMessage)) tvMessage.setText(mMessage);

        if (mTitleRes != 0) tvTitle.setText(getString(mTitleRes));

        if(icon != 0)
            ivIcon.setImageResource(icon);
    }

    private void onPositiveClick() {
        dismiss();
        if (mPositiveListener != null) mPositiveListener.onClick(null);
    }

    private void onNegativeClick() {
        dismiss();
        if (mNegativeListener != null) mNegativeListener.onClick(null);
    }

    @Override
    public void setTitle(@StringRes int _title) {
        if (_title != 0)
            mTitleRes = _title;
    }

    @Override
    public void setMessage(String _title) {
        mMessage = _title;
    }

    @Override
    public void setMessage(@StringRes int _text) {
        mMessageRes = _text;
    }

    @Override
    public void setOnPositiveClickListener(View.OnClickListener _listener) {
        mPositiveListener = _listener;
    }

    @Override
    public void setOnNegativeClickListener(View.OnClickListener _listener) {
        mNegativeListener = _listener;
    }
}
