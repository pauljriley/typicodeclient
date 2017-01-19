package uk.me.paulriley.typicodeclient.view.detail.commentList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import uk.me.paulriley.typicodeclient.R;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;

import static uk.me.paulriley.typicodeclient.support.util.StringFormating.fromHtml;

public class CommentAdapter extends RecyclerView.Adapter<CommentsViewHolder> {
    @Inject TypicodeFacade mTypicodeFacade;

    private final Context context;
    private final ArrayList<CommentResultsModel> comments;

    public CommentAdapter(Context context, ArrayList<CommentResultsModel> comments) {
        this.context = context;
        this.comments = comments;
        ((TypicodeApplication) context.getApplicationContext()).inject(this);
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.comment_card, parent, false);
        CommentsViewHolder viewHolder = new CommentsViewHolder(context, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        CommentResultsModel comment = comments.get(position);
        holder.commentName.setText(fromHtml(comment.getName()));
        holder.comment.setText(fromHtml(comment.getBody()));

        String imageUrl = String.format(Locale.getDefault()
                , "http://api.adorable.io/avatar/%1$d/%2$s"
                , 96
                , comment.getEmail());

        holder.setUserAvatar(imageUrl);
        holder.userName.setText(fromHtml(comment.getEmail()));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
