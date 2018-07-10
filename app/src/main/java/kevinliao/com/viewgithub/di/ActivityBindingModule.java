package kevinliao.com.viewgithub.di;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kevinliao.com.viewgithub.login.LoginActivity;
import kevinliao.com.viewgithub.login.LoginModule;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();

}
