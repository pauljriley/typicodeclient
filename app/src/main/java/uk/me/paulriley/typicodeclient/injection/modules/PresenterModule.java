package uk.me.paulriley.typicodeclient.injection.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.view.detail.DetailActivity;
import uk.me.paulriley.typicodeclient.view.detail.DetailPresenter;
import uk.me.paulriley.typicodeclient.view.detail.DetailPresenterImpl;
import uk.me.paulriley.typicodeclient.view.home.HomeActivity;
import uk.me.paulriley.typicodeclient.view.home.HomePresenter;
import uk.me.paulriley.typicodeclient.view.home.HomePresenterImpl;

@Module(
        injects = {
                HomeActivity.class,
                DetailActivity.class
        },
        complete = false,
        library = true)
public class PresenterModule {
    private final Context mContext;
    private HomePresenterImpl mHomePresenter;
    private DetailPresenterImpl mDetailPresenter;

    public PresenterModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    HomePresenter providesHomePresenter() {
        if (mHomePresenter == null) {
            mHomePresenter = new HomePresenterImpl((TypicodeApplication) mContext);
        }

        return mHomePresenter;
    }

    @Provides
    @Singleton
    DetailPresenter providesDetailPresenter() {
        if (mDetailPresenter == null) {
            mDetailPresenter = new DetailPresenterImpl((TypicodeApplication) mContext);
        }

        return mDetailPresenter;
    }
}
