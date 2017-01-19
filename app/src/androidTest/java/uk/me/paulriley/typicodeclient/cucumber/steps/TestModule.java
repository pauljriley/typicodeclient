package uk.me.paulriley.typicodeclient.cucumber.steps;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import uk.me.paulriley.typicodeclient.injection.modules.ApplicationModule;
import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;
import uk.me.paulriley.typicodeclient.services.model.GeoModel;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.model.UserAddressModel;
import uk.me.paulriley.typicodeclient.services.model.UserCompanyModel;
import uk.me.paulriley.typicodeclient.services.model.UserResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeResults;
import uk.me.paulriley.typicodeclient.view.detail.DetailPresenterImpl;
import uk.me.paulriley.typicodeclient.view.home.HomePresenterImpl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@Module(includes = {
                ApplicationModule.class
        },
        injects = {
                HomePresenterImpl.class,
                DetailPresenterImpl.class
        },
        overrides = true,
        complete = false
)
class TestModule {

    @Mock public TypicodeFacade mockTypicodeFacade;
    @Mock TypicodeResults mockTypicodeResults;

    public TestModule() {
        MockitoAnnotations.initMocks(this);
        when(mockTypicodeFacade.getTypicodeResults()).thenReturn(mockTypicodeResults);
        when(mockTypicodeResults.getPosts()).thenReturn(Observable.just(allPosts()));
        when(mockTypicodeResults.getUser(anyInt())).thenReturn(Observable.just(user()));
        when(mockTypicodeResults.getPostComments(anyInt()))
                .thenReturn(Observable.just(allComments()));
    }

    public static ArrayList<CommentResultsModel> allComments() {
        ArrayList<CommentResultsModel> commentModels = new ArrayList<>();

        commentModels.add(new CommentResultsModel(1, 1, "test name 1", "test email 1"
                , "test body 1"));
        commentModels.add(new CommentResultsModel(2, 2, "test name 2", "test email 2"
                , "test body 2"));
        commentModels.add(new CommentResultsModel(3, 3, "test name 3", "test email 3"
                , "test body 3"));
        commentModels.add(new CommentResultsModel(4, 4, "test name 4", "test email 4"
                , "test body 4"));
        commentModels.add(new CommentResultsModel(5, 5, "test name 5", "test email 5"
                , "test body 5"));
        commentModels.add(new CommentResultsModel(6, 6, "test name 6", "test email 6"
                , "test body 6"));
        commentModels.add(new CommentResultsModel(7, 7, "test name 7", "test email 7"
                , "test body 7"));
        commentModels.add(new CommentResultsModel(8, 8, "test name 8", "test email 8"
                , "test body 8"));
        commentModels.add(new CommentResultsModel(9, 9, "test name 9", "test email 9"
                , "test body 9"));
        commentModels.add(new CommentResultsModel(10, 10, "test name 10", "test email 10"
                , "test body 10"));

        return commentModels;
    }

    public static UserResultsModel user() {
        GeoModel geoModel
                = new GeoModel("test lat", "test lng");

        UserAddressModel userAddressModel = new UserAddressModel("test street"
                , "test suite"
                , "test city"
                , "test zipcode"
                , geoModel);

        UserCompanyModel userCompanyModel = new UserCompanyModel("test name"
                ,"test catchPhrase"
                ,"bs");

        UserResultsModel userModel = new UserResultsModel(1
            , "test name"
            , "test userName"
            , "test email"
            , userAddressModel
            , "test phone"
            , "test website"
            , userCompanyModel);

        return userModel;
    }

    public static ArrayList<PostResultsModel> allPosts() {
        ArrayList<PostResultsModel> postModels = new ArrayList<>();

        postModels.add(new PostResultsModel(1, 1, "title1", "body1"));
        postModels.add(new PostResultsModel(1, 2, "title2", "body2"));
        postModels.add(new PostResultsModel(1, 3, "title3", "body3"));
        postModels.add(new PostResultsModel(1, 4, "title4", "body4"));
        postModels.add(new PostResultsModel(1, 5, "title5", "body5"));
        postModels.add(new PostResultsModel(1, 6, "title6", "body6"));
        postModels.add(new PostResultsModel(1, 7, "title7", "body7"));
        postModels.add(new PostResultsModel(1, 8, "title8", "body8"));
        postModels.add(new PostResultsModel(1, 9, "title9", "body9"));
        postModels.add(new PostResultsModel(1, 10, "title10", "body10"));

        return postModels;
    }

    public static PostResultsModel post() {
        return new PostResultsModel(1, 1, "title1", "body1");
    }

    @Provides
    @Singleton
    TypicodeFacade providesTypicodeFacade() {
        return mockTypicodeFacade;
    }
}
