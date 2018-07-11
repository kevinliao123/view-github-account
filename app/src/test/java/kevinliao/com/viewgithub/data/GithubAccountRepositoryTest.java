package kevinliao.com.viewgithub.data;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import kevinliao.com.viewgithub.network.GithubRepo;
import kevinliao.com.viewgithub.network.GithubService;

import static kevinliao.com.viewgithub.Constants.QUICKEN_LOANS;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class GithubAccountRepositoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    GithubService service;
    GithubAccountRepository repo;

    final List<GithubRepo> MANY_REPO = new ArrayList<>(Arrays.asList(new GithubRepo(),new GithubRepo(),new GithubRepo()));
    @Before
    public void setup() {
        repo = new GithubAccountRepository(service);
    }
    @Test
    public void getRepos() {
        TestObserver<List<GithubRepo>> testSubscriber = new TestObserver<>();
        when(service.getRepositories(anyString())).thenReturn(Observable.fromIterable(MANY_REPO).toList());
        repo.getRepos(QUICKEN_LOANS).subscribe(testSubscriber);
        testSubscriber.assertValue(MANY_REPO);
    }
}