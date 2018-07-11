package kevinliao.com.viewgithub.repolist;

import java.util.List;

import kevinliao.com.viewgithub.BasePresenter;
import kevinliao.com.viewgithub.BaseView;
import kevinliao.com.viewgithub.network.GithubRepo;

public interface RepoListContract {
    interface View extends BaseView<Presenter> {
        void onRepoDataChange(List<GithubRepo> list);

        void showLoadRepoError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadGithubAccount();
    }
}
