package kevinliao.com.viewgithub.login;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Single;
import io.reactivex.observers.DisposableCompletableObserver;
import kevinliao.com.viewgithub.data.UserRepository;
import kevinliao.com.viewgithub.data.local.User;
import kevinliao.com.viewgithub.util.BaseSchedulerProvider;
import kevinliao.com.viewgithub.util.ImmediateSchedulerProvider;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    LoginContract.View mView;
    @Mock
    UserRepository mUserRepo;
    BaseSchedulerProvider mScheduler;
    private LoginContract.Presenter mPresenter;

    private static User VALID_USER;
    private static User INVALID_EMAIL_USER;
    private static User INVALID_PASSWORD_USER;

    @Before
    public void setup() {
        mScheduler = new ImmediateSchedulerProvider();
        mPresenter = new LoginPresenter(mUserRepo, mScheduler);
        mPresenter.takeView(mView);
        VALID_USER = new User(1,"github@github.com","Github123");
        INVALID_EMAIL_USER = new User(1, "123", "Github123");
        INVALID_PASSWORD_USER = new User(1, "github@github.com", "github");
    }

    @Test
    public void loadUser() {
        when(mUserRepo.getUser()).thenReturn(Single.just(VALID_USER));
        mPresenter.loadUser();
        verify(mView).showExistingUserInfo(VALID_USER);
    }

    @Test
    public void validLoginTest(){
        mPresenter.loginUser(VALID_USER);
        verify(mView).proceedToNextPage();
    }

    @Test
    public void invalidEmailLoginTest(){
        mPresenter.loginUser(INVALID_EMAIL_USER);
        verify(mView).showInValidateEmailError();
    }

    @Test
    public void invalidPasswordLoginTest(){
        mPresenter.loginUser(INVALID_PASSWORD_USER);
        verify(mView).showInValidatePasswordError();
    }
}