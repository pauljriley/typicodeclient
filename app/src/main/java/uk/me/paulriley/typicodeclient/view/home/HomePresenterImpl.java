package uk.me.paulriley.typicodeclient.view.home;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeResults;
import uk.me.paulriley.typicodeclient.view.home.homeList.ListResultsAdapterView;

public class HomePresenterImpl implements HomePresenter {
    @Inject ListResultsAdapterView mListResultsAdapter;
    @Inject TypicodeFacade mTypicodeFacade;

    private HomeView mView;

    public HomePresenterImpl(Context mContext) {
        ((TypicodeApplication) mContext).inject(this);
    }

    @Override
    public void initialise(HomeView view) {
        mView = view;
        prepareNewListResults();
    }

    private void prepareNewListResults() {
        mView.setAdapter(mListResultsAdapter);
    }

    @Override
    public void destroy() {
        if (mView != null) mView = null;
    }

    @Override
    public void getPosts() {
        if (mTypicodeFacade != null) {
            TypicodeResults results = mTypicodeFacade.getTypicodeResults();

            results.getPosts()
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<List<PostResultsModel>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(List<PostResultsModel> postResultsModel) {
                            mView.updateData(postResultsModel);
                        }
                    });
        }
    }
}
