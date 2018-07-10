package kevinliao.com.viewgithub.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import kevinliao.com.viewgithub.util.BaseSchedulerProvider;
import kevinliao.com.viewgithub.util.SchedulerProvider;

@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);

    @Singleton
    @Provides
    static BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }
}
