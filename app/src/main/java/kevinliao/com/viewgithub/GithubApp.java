package kevinliao.com.viewgithub;

import android.arch.lifecycle.ProcessLifecycleOwner;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import kevinliao.com.viewgithub.di.DaggerAppComponent;

public class GithubApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner
                .get()
                .getLifecycle()
                .addObserver(new ApplicationObserver(getApplicationContext()));
    }
}
