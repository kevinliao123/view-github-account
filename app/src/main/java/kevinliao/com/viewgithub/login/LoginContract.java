package kevinliao.com.viewgithub.login;

import kevinliao.com.viewgithub.BasePresenter;
import kevinliao.com.viewgithub.BaseView;
import kevinliao.com.viewgithub.data.local.User;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showValidationRationale();

        void proceedToNextPage();

        void showExistingUserInfo(User user);

        void showInValidateEmailError();

        void showInValidatePasswordError();

        void showDatabaseInsertionError();

        void clearInputError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadUser();

        void loginUser(User user);
    }
}
