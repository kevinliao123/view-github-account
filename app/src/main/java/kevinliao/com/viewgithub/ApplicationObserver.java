package kevinliao.com.viewgithub;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import java.lang.ref.WeakReference;

import kevinliao.com.viewgithub.login.LoginActivity;

import static kevinliao.com.viewgithub.Constants.FORCE_LOGOUT_WINDOW;

public class ApplicationObserver implements LifecycleObserver {
    Handler mHandler;
    Runnable mLogoutRunnable;

    ApplicationObserver(final Context context) {
        mHandler = new Handler();
        mLogoutRunnable = new LogoutRunnable(context);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onForeground() {
        mHandler.removeCallbacks(mLogoutRunnable);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onBackGround() {
        mHandler.postDelayed(mLogoutRunnable, FORCE_LOGOUT_WINDOW);
    }

    private static class LogoutRunnable implements Runnable {
        WeakReference<Context> weakReference;
        public LogoutRunnable(Context context) {
            weakReference = new WeakReference<>(context);
        }

        @Override
        public void run() {
            Intent intent = new Intent(weakReference.get(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            weakReference.get().startActivity(intent);
        }
    }
}
