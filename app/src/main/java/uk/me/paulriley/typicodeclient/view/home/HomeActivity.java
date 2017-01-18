package uk.me.paulriley.typicodeclient.view.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.me.paulriley.typicodeclient.R;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.support.util.CountingIdlingResourceListener;
import uk.me.paulriley.typicodeclient.view.home.homeList.ListResultsAdapter;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private static CountingIdlingResourceListener sIdlingNotificationListener;

    @Inject HomePresenter mPresenter;

    @BindView(R.id.posts_list) RecyclerView mResultsView;
    @BindView(R.id.progress) ProgressBar mProgressBar;

    private LinearLayoutManager mLayoutManager;
    private ArrayList<PostResultsModel> posts = new ArrayList<>();
    private ListResultsAdapter mResultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TypicodeApplication) getApplication()).inject(this);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initialiseListView();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }

        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.initialise(this);
        mPresenter.getPosts();
    }

    private void initialiseListView() {
        mLayoutManager = new LinearLayoutManager(this);
        mResultsView.setLayoutManager(mLayoutManager);
        mResultsAdapter = new ListResultsAdapter(this, posts);
        mResultsView.setAdapter(mResultsAdapter);
    }

    public static void setIdlingNotificationListener(CountingIdlingResourceListener idlingNotificationListener) {
        sIdlingNotificationListener = idlingNotificationListener;
    }

    @Override
    public void updateData(ArrayList<PostResultsModel> postResults) {
        posts.clear();
        posts.addAll(postResults);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResultsAdapter.notifyDataSetChanged();
            }
        });
    }
}
