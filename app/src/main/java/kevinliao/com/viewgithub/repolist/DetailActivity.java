package kevinliao.com.viewgithub.repolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kevinliao.com.viewgithub.R;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.web_view)
    WebView mWebView;

    @BindView(R.id.repo_name)
    TextView mRepoName;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mUnbinder = ButterKnife.bind(this);
        Intent message = getIntent();
        String url = message.getStringExtra(getString(R.string.url));
        String fullName = message.getStringExtra(getString(R.string.full_name));
        if (!TextUtils.isEmpty(url)) {
            mWebView.loadUrl(url);
        }
        if (!TextUtils.isEmpty(fullName)) {
            mRepoName.setText("Repo Name: " + fullName);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
