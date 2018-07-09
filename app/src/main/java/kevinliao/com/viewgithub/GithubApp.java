package kevinliao.com.viewgithub;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import kevinliao.com.viewgithub.di.DaggerAppComponent;

public class GithubApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
