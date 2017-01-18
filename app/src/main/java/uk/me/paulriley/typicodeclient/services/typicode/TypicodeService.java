package uk.me.paulriley.typicodeclient.services.typicode;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.model.UserResultsModel;

public interface TypicodeService {
    @GET("/posts")
    Observable<ArrayList<PostResultsModel>> getPosts();

    @GET("/posts/{PostID}/comments")
    Observable<ArrayList<CommentResultsModel>> getPostComments(@Path("PostID") int postID);

    @GET("/users/{UserID}")
    Observable<UserResultsModel> getUser(@Path("UserID") int userID);
}
