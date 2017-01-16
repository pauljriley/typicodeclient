package uk.me.paulriley.typicodeclient.injection.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.view.home.HomePresenterImpl;
import uk.me.paulriley.typicodeclient.view.home.homeList.ListResultsAdapter;
import uk.me.paulriley.typicodeclient.view.home.homeList.ListResultsAdapterView;

@Module(
        injects = {
                HomePresenterImpl.class
        },
        complete = false,
        library = true)
public class ApplicationModule {
    private final TypicodeApplication mApplication;
    private ListResultsAdapter mListResultsAdapter;
    private TypicodeFacade mTypicodeFacade;

    public ApplicationModule(TypicodeApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    ListResultsAdapterView providesListResultsAdapterView() {
        if (mListResultsAdapter == null) {
            mListResultsAdapter = new ListResultsAdapter();
        }

        return mListResultsAdapter;
    }

    @Provides
    @Singleton
    TypicodeFacade providesTypicodeFacade() {
        if (mTypicodeFacade == null) {
            mTypicodeFacade = new TypicodeFacade();
        }

        return mTypicodeFacade;
    }
}
