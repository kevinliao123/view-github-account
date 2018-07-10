package kevinliao.com.viewgithub.data;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import kevinliao.com.viewgithub.data.local.UserDao;
import kevinliao.com.viewgithub.data.local.UserDatabase;
import kevinliao.com.viewgithub.data.local.UserLocalDataSource;
import kevinliao.com.viewgithub.util.BaseSchedulerProvider;
import kevinliao.com.viewgithub.util.SchedulerProvider;

@Module
public abstract class DataModule {

    @Singleton
    @Binds
    abstract UserDataSource provideUserLocalDataSource(UserLocalDataSource dataSource);

    @Singleton
    @Provides
    static UserRepository provideUserRepository(UserLocalDataSource userLocalDataSource) {
        return new UserRepository(userLocalDataSource);
    }

    @Singleton
    @Provides
    static UserDatabase provideUserDatabase(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user.db")
                .build();
    }

    @Singleton
    @Provides
    static UserDao provideUserDao(UserDatabase db) {
        return db.userDao();
    }


}
