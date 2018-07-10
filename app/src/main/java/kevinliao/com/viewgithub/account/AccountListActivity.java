package kevinliao.com.viewgithub.account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dagger.android.support.DaggerAppCompatActivity;
import kevinliao.com.viewgithub.R;

public class AccountListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

    }
}
