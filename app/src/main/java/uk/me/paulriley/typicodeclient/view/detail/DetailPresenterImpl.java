package uk.me.paulriley.typicodeclient.view.detail;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeResults;

public class DetailPresenterImpl implements DetailPresenter {
    @Inject TypicodeFacade mTypicodeFacade;

    private DetailView mView;

    public DetailPresenterImpl(Context mContext) {
        ((TypicodeApplication) mContext).inject(this);
    }

    @Override
    public void initialise(DetailView view) {
        mView = view;
    }

    @Override
    public void destroy() {
        if (mView != null) mView = null;
    }

    @Override
    public void getPost() {
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
                        }
                    });
        }
    }
}