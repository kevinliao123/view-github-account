package kevinliao.com.viewgithub.login;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import kevinliao.com.viewgithub.data.UserRepository;
import kevinliao.com.viewgithub.data.local.User;
import kevinliao.com.viewgithub.util.BaseSchedulerProvider;
import kevinliao.com.viewgithub.util.ValidateUtils;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;
    private UserRepository mUserRepo;
    private CompositeDisposable mCompositeDisposable;
    private BaseSchedulerProvider mScheduler;

    @Inject
    public LoginPresenter(UserRepository userRepository, BaseSchedulerProvider schedulerProvider) {
        mUserRepo = userRepository;
        mScheduler = schedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadUser() {
        mCompositeDisposable.add(mUserRepo.getUser()
                .subscribeOn(mScheduler.io())
                .observeOn(mScheduler.ui())
                .subscribeWith(new DisposableSingleObserver<User>() {

                    @Override
                    public void onSuccess(User user) {
                        if (user != null) mView.showExistingUserInfo(user);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));

    }

    @Override
    public void loginUser(User user) {
        mView.clearInputError();
        if (!ValidateUtils.isValidateEmail(user.getEmail())) {
            mView.showInValidateEmailError();
            return;
        }
        if (!ValidateUtils.isValidatePassword(user.getPassword())) {
            mView.showInValidatePasswordError();
            return;
        }

        mCompositeDisposable.add(Completable.fromAction(() ->
                mUserRepo.insertUser(user)
        ).subscribeOn(mScheduler.io())
                .observeOn(mScheduler.ui())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        mView.proceedToNextPage();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showDatabaseInsertionError();
                    }
                }));
    }

    @Override
    public void takeView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void subscribe() {
        loadUser();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
