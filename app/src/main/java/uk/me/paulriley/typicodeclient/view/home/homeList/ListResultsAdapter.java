package uk.me.paulriley.typicodeclient.view.home.homeList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;
import uk.me.paulriley.typicodeclient.R;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.model.UserResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeResults;
import uk.me.paulriley.typicodeclient.view.detail.DetailActivity;

import static uk.me.paulriley.typicodeclient.support.util.StringFormating.fromHtml;

public class ListResultsAdapter extends RecyclerView.Adapter<ListResultsViewHolder> {
    @Inject TypicodeFacade mTypicodeFacade;

    private final Context context;
    private ArrayList<PostResultsModel> posts;

    public ListResultsAdapter(Context context, ArrayList<PostResultsModel> posts) {
        this.context = context;
        this.posts = posts;
        ((TypicodeApplication) context.getApplicationContext()).inject(this);
    }

    @Override
    public ListResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.post_card, parent, false);
        ListResultsViewHolder viewHolder = new ListResultsViewHolder(context, view);
        view.setOnClickListener(mOnClickViewHolder(context, viewHolder));
        return viewHolder;
    }

    private View.OnClickListener mOnClickViewHolder(final Context context
            , final ListResultsViewHolder viewHolder) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    PostResultsModel post = posts.get(position);
                    context.startActivity(DetailActivity.buildIntent(context, post));
                }
            }
        };

        return onClickListener;
    }

    @Override
    public void onBindViewHolder(final ListResultsViewHolder holder, int position) {
        PostResultsModel post = posts.get(position);
        holder.title.setText(fromHtml(post.getTitle()));

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
                            String imageUrl = String.format(Locale.getDefault()
                                    , "http://api.adorable.io/avatar/%1$d/%2$s"
                                    , 96
                                    , userResultsModels.getEmail());

                            holder.setUserAvatar(imageUrl);
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
