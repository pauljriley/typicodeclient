package uk.me.paulriley.typicodeclient.cucumber.steps;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import uk.me.paulriley.typicodeclient.injection.modules.ApplicationModule;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeResults;
import uk.me.paulriley.typicodeclient.view.detail.DetailPresenterImpl;
import uk.me.paulriley.typicodeclient.view.home.HomePresenterImpl;

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

    @Provides
    @Singleton
    TypicodeFacade providesTypicodeFacade() {
        return mockTypicodeFacade;
    }
}
