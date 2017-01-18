package uk.me.paulriley.typicodeclient.view.detail;

public interface DetailPresenter {
    void initialise(DetailView detailView);
    void destroy();
    void getPost();
    void getComments(int postId);
}
