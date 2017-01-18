package uk.me.paulriley.typicodeclient.view.home.homeList;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.devspark.robototextview.widget.RobotoTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import uk.me.paulriley.typicodeclient.R;

public class ListResultsViewHolder extends RecyclerView.ViewHolder {
    private final Context context;

    public RobotoTextView title;
    public SimpleDraweeView userAvatar;

    public ListResultsViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        title = (RobotoTextView) itemView.findViewById(R.id.post);
        userAvatar = (SimpleDraweeView) itemView.findViewById(R.id.post_user);
    }

    public void setUserAvatar(String imageUrl) {
        Log.d("---->" + this, "imageUrl: " + imageUrl);

        Uri uri = Uri.parse(imageUrl);
        userAvatar.setImageURI(uri);
    }
}
