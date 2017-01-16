package uk.me.paulriley.typicodeclient.view.home.homeList;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.devspark.robototextview.widget.RobotoTextView;

import uk.me.paulriley.typicodeclient.R;

public class ListResultsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public RobotoTextView title;

    public ListResultsViewHolder(View itemView) {
        super(itemView);
        title = (RobotoTextView) itemView.findViewById(R.id.post);
    }

    @Override
    public void onClick(View v) {

    }
}
