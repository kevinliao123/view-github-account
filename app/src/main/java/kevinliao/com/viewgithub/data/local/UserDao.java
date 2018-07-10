package kevinliao.com.viewgithub.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserDao {
    /**
     * get the only user from the table
     *
     * @return Single Observer that emit 1 user
     */
    @Query("SELECT * FROM Users LIMIT 1")
    Single<User> getUser();

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

}