package kevinliao.com.viewgithub.repolist;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import kevinliao.com.viewgithub.Constants;
import kevinliao.com.viewgithub.data.GithubAccountRepository;
import kevinliao.com.viewgithub.network.GithubRepo;
import kevinliao.com.viewgithub.util.BaseSchedulerProvider;

public class RepoListPresenter implements RepoListContract.Presenter {
    private RepoListContract.View mView;
    private GithubAccountRepository mGithubAccountRepository;
    private BaseSchedulerProvider mScheduler;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    public RepoListPresenter(GithubAccountRepository githubAccountRepository, BaseSchedulerProvider schedulerProvider) {
        mGithubAccountRepository = githubAccountRepository;
        mScheduler = schedulerProvider;

    }

    @Override
    public void takeView(RepoListContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void subscribe() {
        loadGithubAccount();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void loadGithubAccount() {
        mCompositeDisposable.add(
                mGithubAccountRepository.getRepos(Constants.QUICKEN_LOANS)
                        .subscribeOn(mScheduler.io())
                        .observeOn(mScheduler.ui())
                        .subscribeWith(new DisposableSingleObserver<List<GithubRepo>>() {
                            @Override
                            public void onSuccess(List<GithubRepo> githubRepos) {
                                mView.onRepoDataChange(githubRepos);
                            }

                            @Override
                            public void onError(Throwable e) {
                                mView.showLoadRepoError();
                            }
                        })
        );
    }
}
