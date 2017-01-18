package uk.me.paulriley.typicodeclient.view.detail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import uk.me.paulriley.typicodeclient.R;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.model.UserResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeResults;
import uk.me.paulriley.typicodeclient.view.detail.commentList.CommentAdapter;

public class DetailActivity extends AppCompatActivity implements DetailView {
    private static final String POST_RESULT_MODEL = DetailActivity.class.getSimpleName()
            + "_post_result_model";

    @Inject TypicodeFacade mTypicodeFacade;
    @Inject DetailPresenter mPresenter;

    @BindView(R.id.post_title) RobotoTextView mTitle;
    @BindView(R.id.post_body) RobotoTextView mBody;
    @BindView(R.id.post_user_avatar) SimpleDraweeView mAvatar;
    @BindView(R.id.post_user_name) RobotoTextView mUserName;
    @BindView(R.id.comments) RecyclerView mCommentView;

    private LinearLayoutManager mLayoutManager;
    private CommentAdapter mCommentAdapter;
    private ArrayList<CommentResultsModel> comments = new ArrayList<>();

    public static Intent buildIntent(Context activityContext, PostResultsModel postResultsModel) {
        Intent intent = new Intent(activityContext, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailActivity.POST_RESULT_MODEL, postResultsModel);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TypicodeApplication) getApplication()).inject(this);

        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        initialiseListView();

        if (savedInstanceState == null) {
            if (getIntent().getExtras() != null) {
                extractAndProcessIntentExtras(getIntent());
            }
        }
    }

    private void extractAndProcessIntentExtras(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            PostResultsModel post = (PostResultsModel)
                    bundle.getSerializable(DetailActivity.POST_RESULT_MODEL);

            populatePostDetails(post);
        }
    }

    private void populatePostDetails(PostResultsModel post) {
        mTitle.setText(post.getTitle());
        mBody.setText(post.getBody());

        if (mTypicodeFacade != null) {
            TypicodeResults results = mTypicodeFacade.getTypicodeResults();

            results.getUser(post.getUserId())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<UserResultsModel>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(UserResultsModel userResultsModels) {
                            mUserName.setText(userResultsModels.getUsername());
                            String imageUrl = String.format(Locale.getDefault()
                                    , "http://api.adorable.io/avatar/%1$d/%2$s"
                                    , 96
                                    , userResultsModels.getEmail());

                            Uri uri = Uri.parse(imageUrl);
                            mAvatar.setImageURI(uri);
                        }
                    });
        }

        mPresenter.getComments(post.getId());
    }

    private void initialiseListView() {
        mLayoutManager = new LinearLayoutManager(this);
        mCommentView.setLayoutManager(mLayoutManager);
        mCommentAdapter = new CommentAdapter(this, comments);
        mCommentView.setAdapter(mCommentAdapter);
    }

    @Override
    public void updateData(ArrayList<CommentResultsModel> postComments) {
        comments.clear();
        comments.addAll(postComments);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCommentAdapter.notifyDataSetChanged();
            }
        });
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

        if (mPresenter == null) {
            ((TypicodeApplication) getApplication()).inject(this);
        }

        mPresenter.initialise(this);
        mPresenter.getPost();
    }
}
