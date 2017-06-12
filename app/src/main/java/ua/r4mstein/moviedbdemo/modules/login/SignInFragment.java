package ua.r4mstein.moviedbdemo.modules.login;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;

public class SignInFragment extends BaseFragment<SignInPresenter>
        implements SignInPresenter.SignInView {

    private TextInputLayout tilUsername;
    private TextInputLayout tilPass;

    private TextInputEditText tetUserName;
    private TextInputEditText tetPass;
    private Button btnLogin;

    @Override
    protected int getTitle() {
        return R.string.title_login;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected SignInPresenter initPresenter() {
        return new SignInPresenter();
    }

    @Override
    protected void findUI(View rootView) {
        tilUsername = (TextInputLayout) rootView.findViewById(R.id.til_user_name_container_FSI);
        tilPass = (TextInputLayout) rootView.findViewById(R.id.til_password_container_FSI);

        tetUserName = (TextInputEditText) rootView.findViewById(R.id.tiet_user_name_FSI);
        tetPass = (TextInputEditText) rootView.findViewById(R.id.tiet_password_FSI);
        btnLogin = (Button) rootView.findViewById(R.id.btn_login_FSI);
    }

    @Override
    protected void setupUI() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().btnLoginClicked();
            }
        });
    }

    @Override
    public void hideToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public String getUserName() {
        return tetUserName.getText().toString().trim();
    }

    @Override
    public String getPass() {
        return tetPass.getText().toString().trim();
    }
}
