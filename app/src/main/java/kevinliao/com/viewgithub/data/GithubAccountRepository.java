package kevinliao.com.viewgithub.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import kevinliao.com.viewgithub.network.GithubRepo;
import kevinliao.com.viewgithub.network.GithubService;

public class GithubAccountRepository {

    private GithubService mGithubService;
    @Inject
    public GithubAccountRepository(GithubService githubService) {
        mGithubService = githubService;
    }

    public Single<List<GithubRepo>> getRepos(String user) {
        return mGithubService.getRepositories(user);
    }
}
