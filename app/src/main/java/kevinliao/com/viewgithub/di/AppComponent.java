package kevinliao.com.viewgithub.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import kevinliao.com.viewgithub.GithubApp;
import kevinliao.com.viewgithub.data.DataModule;
import kevinliao.com.viewgithub.network.NetworkModule;

@Singleton
@Component(modules = {
        NetworkModule.class,
        DataModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<GithubApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
