package uk.me.paulriley.typicodeclient.view.detail.commentList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import uk.me.paulriley.typicodeclient.R;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;

public class CommentAdapter extends RecyclerView.Adapter {
    @Inject TypicodeFacade mTypicodeFacade;

    private final Context context;
    private final ArrayList<CommentResultsModel> comments;

    public CommentAdapter(Context context, ArrayList<CommentResultsModel> comments) {
        this.context = context;
        this.comments = comments;
        ((TypicodeApplication) context.getApplicationContext()).inject(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.comment_card, parent, false);
        CommentsViewHolder viewHolder = new CommentsViewHolder(context, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommentResultsModel comment = comments.get(position);

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
