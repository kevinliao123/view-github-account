package kevinliao.com.viewgithub.repolist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kevinliao.com.viewgithub.R;
import kevinliao.com.viewgithub.network.GithubRepo;

public class ReposListAdapter extends RecyclerView.Adapter<ReposListAdapter.RepoViewHolder> {
    List<GithubRepo> mRepoList = new ArrayList<>();

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.github_list_row, parent, false);

        return new RepoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(mRepoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    public void setRepoList(List<GithubRepo> list) {
        if (list == null) return;
        mRepoList.clear();
        mRepoList.addAll(list);
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repo_name)
        TextView mRepoName;

        @BindView(R.id.repo_updated_date)
        TextView mRepoUpdatedDate;

        @BindView(R.id.repo_description)
        TextView mRepoDescription;

        String mUrl;
        String mFullName;

        public RepoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(v -> {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(context.getString(R.string.url), mUrl);
                intent.putExtra(context.getString(R.string.full_name), mFullName);
                context.startActivity(intent);

            });
        }

        public void bind(GithubRepo repo) {
            mRepoName.setText(repo.getFullName());
            mRepoUpdatedDate.setText(repo.getUpdatedAt());
            mRepoDescription.setText(repo.getDescription());
            mUrl = repo.getUrl();
            mFullName = repo.getFullName();
        }
    }
}
