package uk.me.paulriley.typicodeclient.cucumber.steps;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.me.paulriley.typicodeclient.injection.modules.ApplicationModule;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.view.home.HomePresenter;
import uk.me.paulriley.typicodeclient.view.home.HomePresenterImpl;

@Module(includes = {
                ApplicationModule.class
        },
        injects = {
                HomePresenterImpl.class,
        },
        overrides = true,
        complete = false
)
class TestModule {

    @Mock public TypicodeFacade mockTypicodeFacade;

    public TestModule() {
        MockitoAnnotations.initMocks(this);

    }

    @Provides
    @Singleton
    HomePresenter providesHomePrsenter() {
        return mockHomePresenter;
    }
}
