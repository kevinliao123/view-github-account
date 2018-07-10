package kevinliao.com.viewgithub.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import kevinliao.com.viewgithub.R;
import kevinliao.com.viewgithub.account.AccountListActivity;
import kevinliao.com.viewgithub.data.local.User;

public class LoginActivity extends DaggerAppCompatActivity implements LoginContract.View {
    @BindView(R.id.username)
    EditText mUserNameEditText;

    @BindView(R.id.password)
    EditText mPasswordEditText;

    @BindView(R.id.usernameWrapper)
    TextInputLayout mUserNameWrapper;

    @BindView(R.id.passwordWrapper)
    TextInputLayout mPasswordWrapper;

    private Unbinder mUnbinder;

    @Inject
    LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.takeView(this);
        mPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.dropView();
        mPresenter.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked(View view) {
        String email = mUserNameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        mPresenter.loginUser(new User(0, email, password));
    }

    @OnClick(R.id.help_text_view)
    public void onHelpButtonClicked(View view) {
        showValidationRationale();
    }

    @Override
    public void showValidationRationale() {
        DialogFragment newFragment = new ValidationRuleDialogFragment();
        newFragment.show(getSupportFragmentManager(), "validation");
    }

    @Override
    public void proceedToNextPage() {
        startActivity(new Intent(this, AccountListActivity.class));
    }

    @Override
    public void showExistingUserInfo(User user) {
        mUserNameEditText.setText(user.getEmail());
        mPasswordEditText.setText(user.getPassword());
    }

    @Override
    public void showInValidateEmailError() {
        mUserNameWrapper.setError(getString(R.string.invalid_email));
    }

    @Override
    public void showInValidatePasswordError() {
        mPasswordWrapper.setError(getString(R.string.invalid_password));
    }

    @Override
    public void clearInputError() {
        mUserNameWrapper.setError(null);
        mPasswordWrapper.setError(null);
    }

    @Override
    public void showDatabaseInsertionError() {
        Toast.makeText(this, R.string.database_insertion_error, Toast.LENGTH_LONG).show();
    }
}
