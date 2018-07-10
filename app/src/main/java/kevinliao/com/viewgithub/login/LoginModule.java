package kevinliao.com.viewgithub.login;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginModule {
    @Binds abstract LoginContract.Presenter loginPresenter(LoginPresenter presenter);
}
