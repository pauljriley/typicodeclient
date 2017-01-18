package uk.me.paulriley.typicodeclient.injection.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.view.detail.DetailActivity;
import uk.me.paulriley.typicodeclient.view.detail.DetailPresenterImpl;
import uk.me.paulriley.typicodeclient.view.detail.commentList.CommentAdapter;
import uk.me.paulriley.typicodeclient.view.home.HomePresenterImpl;
import uk.me.paulriley.typicodeclient.view.home.homeList.ListResultsAdapter;

@Module(
        injects = {
                HomePresenterImpl.class,
                DetailPresenterImpl.class,
                ListResultsAdapter.class,
                DetailActivity.class,
                CommentAdapter.class
        },
        complete = false,
        library = true)
public class ApplicationModule {
    private TypicodeFacade mTypicodeFacade;

    @Provides
    @Singleton
    TypicodeFacade providesTypicodeFacade() {
        if (mTypicodeFacade == null) {
            mTypicodeFacade = new TypicodeFacade();
        }

        return mTypicodeFacade;
    }
}
