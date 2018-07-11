package kevinliao.com.viewgithub.repolist;

import dagger.Binds;
import dagger.Module;
import kevinliao.com.viewgithub.di.ActivityScoped;

@Module
public abstract class RepoListModule {
    @ActivityScoped
    @Binds
    abstract RepoListContract.Presenter repoListPresenter(RepoListPresenter presenter);
}
