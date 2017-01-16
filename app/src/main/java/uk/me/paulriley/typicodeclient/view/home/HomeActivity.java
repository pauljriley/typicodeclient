package uk.me.paulriley.typicodeclient.view.home;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.me.paulriley.typicodeclient.R;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.support.util.CountingIdlingResourceListener;
import uk.me.paulriley.typicodeclient.view.home.homeList.ListResultsAdapter;
import uk.me.paulriley.typicodeclient.view.home.homeList.ListResultsAdapterView;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private static CountingIdlingResourceListener sIdlingNotificationListener;

    @Inject HomePresenter mPresenter;

    @BindView(R.id.posts_list) RecyclerView mResultsView;
    @BindView(R.id.progress) ProgressBar mProgressBar;

    private GridLayoutManager mLayoutManager;
    private List<PostResultsModel> posts = new ArrayList<>();
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
        mLayoutManager = new GridLayoutManager(this, 1);
        mResultsView.addItemDecoration(itemDecoration);
        mResultsView.setLayoutManager(mLayoutManager);
        mResultsAdapter = new ListResultsAdapter();
        mResultsView.setAdapter(mResultsAdapter);
    }

    RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            int position = parent.getChildAdapterPosition(view);
            int column = position % mLayoutManager.getSpanCount();
            int spacing = dpToPixels(8);

            outRect.left = spacing - column * spacing / mLayoutManager.getSpanCount();
            outRect.right = (column + 1) * spacing / mLayoutManager.getSpanCount();
            outRect.bottom = dpToPixels(8);

            if (position < mLayoutManager.getSpanCount()) {
                outRect.top = spacing;
            }
            outRect.bottom = spacing;
        }
    };

    protected int dpToPixels(int dps){
        return ((Float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, getResources().getDisplayMetrics())).intValue();
    }

    public static void setIdlingNotificationListener(CountingIdlingResourceListener idlingNotificationListener) {
        sIdlingNotificationListener = idlingNotificationListener;
    }

    @Override
    public void setAdapter(ListResultsAdapterView listResultsAdapter) {
        if (listResultsAdapter != null) {
            listResultsAdapter.initialise(mPresenter);
        }

        mResultsView.setAdapter((ListResultsAdapter) listResultsAdapter);
    }

    @Override
    public void updateData(List<PostResultsModel> postResults) {
        Log.d("---->" + this, "updateData(" + postResults.size() + ")");
        posts.addAll(postResults);
        Log.d("---->" + this, "posts.addAll(postResults): " + posts.size());
        mResultsAdapter.setData(postResults);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("---->" + this, "run()");
                mResultsAdapter.notifyDataSetChanged();
            }
        });
    }
}
