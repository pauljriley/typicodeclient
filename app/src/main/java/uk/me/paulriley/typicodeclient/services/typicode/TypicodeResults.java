package uk.me.paulriley.typicodeclient.services.typicode;

import java.util.List;

import rx.Observable;
import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.model.UserResultsModel;

public class TypicodeResults {
    private final TypicodeService icndb;

    public TypicodeResults(TypicodeService icndb) {
        this.icndb = icndb;
    }

    public Observable<List<PostResultsModel>> getPosts() {
        return icndb.getPosts();
    }

    public Observable<List<UserResultsModel>> getUsers() {
        return icndb.getUsers();
    }

    public Observable<List<CommentResultsModel>> getComments() {
        return icndb.getComments();
    }
}
