package kevinliao.com.viewgithub.repolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import kevinliao.com.viewgithub.R;
import kevinliao.com.viewgithub.login.LoginActivity;
import kevinliao.com.viewgithub.network.GithubRepo;

public class RepoListActivity extends DaggerAppCompatActivity implements RepoListContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    RepoListContract.Presenter mPresenter;

    private Unbinder mUnbinder;
    private ReposListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        mUnbinder = ButterKnife.bind(this);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mAdapter = new ReposListAdapter();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.subscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.dropView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mUnbinder.unbind();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRepoDataChange(List<GithubRepo> list) {
        mAdapter.setRepoList(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadRepoError() {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
    }
}
