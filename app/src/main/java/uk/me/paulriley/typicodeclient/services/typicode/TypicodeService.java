package uk.me.paulriley.typicodeclient.services.typicode;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.model.UserResultsModel;

public interface TypicodeService {
    @GET("/posts")
    Observable<List<PostResultsModel>> getPosts();

    @GET("/users")
    Observable<List<UserResultsModel>> getUsers();

    @GET("/comments")
    Observable<List<CommentResultsModel>> getComments();
}
