package uk.me.paulriley.typicodeclient.view.home;

public interface HomePresenter {
    void initialise(HomeView view);
    void destroy();
    void getPosts();
}
