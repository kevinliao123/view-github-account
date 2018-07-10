package kevinliao.com.viewgithub.data.local;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import kevinliao.com.viewgithub.data.UserDataSource;

@Singleton
public class UserLocalDataSource implements UserDataSource {
    UserDao mUserDao;

    @Inject
    public UserLocalDataSource(UserDao userDao) {
        mUserDao = userDao;
    }

    @Override
    public Single<User> getUser() {
        return mUserDao.getUser();
    }

    @Override
    public void insertUser(User user) {
        mUserDao.insertUser(user);
    }
}
