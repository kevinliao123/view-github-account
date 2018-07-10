package kevinliao.com.viewgithub.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import kevinliao.com.viewgithub.data.local.User;
import kevinliao.com.viewgithub.data.local.UserLocalDataSource;

@Singleton
public class UserRepository implements UserDataSource {

    UserLocalDataSource mUserLocalDataSource;

    @Inject
    public UserRepository(UserLocalDataSource userLocalDataSource) {
        mUserLocalDataSource = userLocalDataSource;
    }
    @Override
    public Single<User> getUser() {
        return mUserLocalDataSource.getUser();
    }

    @Override
    public void insertUser(User user) {
         mUserLocalDataSource.insertUser(user);
    }
}
