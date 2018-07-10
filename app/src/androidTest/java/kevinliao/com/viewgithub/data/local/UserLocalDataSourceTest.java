package kevinliao.com.viewgithub.data.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserLocalDataSourceTest {

    private UserLocalDataSource mLocalDataSource;

    private UserDatabase mDatabase;
    @Before
    public void setup() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                UserDatabase.class)
                .build();
        UserDao userDao = mDatabase.userDao();
        mLocalDataSource = new UserLocalDataSource(userDao);
    }
    @Test
    public void insert_getUser() {
        User user = new User(1,"github@gmail.com","12345");
        mLocalDataSource.insertUser(user);
        TestObserver<User> testSubscriber = new TestObserver<>();
        mLocalDataSource.getUser().subscribe(testSubscriber);
        testSubscriber.assertResult(user);
    }
}