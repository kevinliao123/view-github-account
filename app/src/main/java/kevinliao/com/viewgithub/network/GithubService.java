package kevinliao.com.viewgithub.network;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("users/{user}/repos")
    Single<List<GithubRepo>> getRepositories(@Path("user") String userName);
}