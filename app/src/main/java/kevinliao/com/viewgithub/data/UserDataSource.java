package kevinliao.com.viewgithub.data;

import io.reactivex.Completable;
import io.reactivex.Single;
import kevinliao.com.viewgithub.data.local.User;

public interface UserDataSource {

    /**
     * Gets the user from the data source.
     *
     * @return Single Observer that emit 1 user
     */
    Single<User> getUser();

    /**
     * Inserts the user into the data source.
     *
     * @param user the user to be inserted.
     */
    void insertUser(User user);
}
