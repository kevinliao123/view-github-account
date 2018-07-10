package kevinliao.com.viewgithub;

public interface BasePresenter<T> {
    void takeView(T view);

    void dropView();

    void subscribe();

    void unsubscribe();
}
