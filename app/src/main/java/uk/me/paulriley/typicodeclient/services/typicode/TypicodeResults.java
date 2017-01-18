package uk.me.paulriley.typicodeclient.services.typicode;

import java.util.ArrayList;

import rx.Observable;
import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.model.UserResultsModel;

public class TypicodeResults {
    private final TypicodeService icndb;

    public TypicodeResults(TypicodeService icndb) {
        this.icndb = icndb;
    }

    public Observable<ArrayList<PostResultsModel>> getPosts() {
        return icndb.getPosts();
    }

    public Observable<ArrayList<CommentResultsModel>> getPostComments(int postID) {
        return icndb.getPostComments(postID);
    }

    public Observable<UserResultsModel> getUser(int userID) {
        return icndb.getUser(userID);
    }
}
