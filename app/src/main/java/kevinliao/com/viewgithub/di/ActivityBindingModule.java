package kevinliao.com.viewgithub.di;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kevinliao.com.viewgithub.login.LoginActivity;
import kevinliao.com.viewgithub.login.LoginModule;
import kevinliao.com.viewgithub.repolist.RepoListActivity;
import kevinliao.com.viewgithub.repolist.RepoListModule;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = RepoListModule.class)
    abstract RepoListActivity repoListActivity();

}
