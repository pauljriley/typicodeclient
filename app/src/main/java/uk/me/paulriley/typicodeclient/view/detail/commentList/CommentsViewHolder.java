package uk.me.paulriley.typicodeclient.view.detail.commentList;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.devspark.robototextview.widget.RobotoTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import uk.me.paulriley.typicodeclient.R;

class CommentsViewHolder extends RecyclerView.ViewHolder {
    private final Context context;

    public SimpleDraweeView userAvatar;
    public RobotoTextView userName;
    public RobotoTextView commentName;
    public RobotoTextView comment;

    public CommentsViewHolder(Context context, View view) {
        super(view);
        this.context = context;
        userAvatar = (SimpleDraweeView) view.findViewById(R.id.comment_user_avatar);
        userName = (RobotoTextView) view.findViewById(R.id.comment_user_name);
        commentName = (RobotoTextView) view.findViewById(R.id.comment_name);
        comment = (RobotoTextView) view.findViewById(R.id.comment);
    }

    public void setUserAvatar(String imageUrl) {
        Uri uri = Uri.parse(imageUrl);
        userAvatar.setImageURI(uri);
    }
}
