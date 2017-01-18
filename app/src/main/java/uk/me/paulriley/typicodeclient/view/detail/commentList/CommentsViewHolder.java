package uk.me.paulriley.typicodeclient.view.detail.commentList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class CommentsViewHolder extends RecyclerView.ViewHolder {
    private final Context context;

    public CommentsViewHolder(Context context, View view) {
        super(view);
        this.context = context;
    }
}
