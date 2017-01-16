package uk.me.paulriley.typicodeclient.view.home.homeList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import uk.me.paulriley.typicodeclient.R;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.view.home.HomePresenter;

import static uk.me.paulriley.typicodeclient.support.util.StringFormating.fromHtml;

public class ListResultsAdapter extends RecyclerView.Adapter<ListResultsViewHolder> implements ListResultsAdapterView {
    private List<PostResultsModel> posts = new ArrayList<>();

    @Override
    public ListResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.post_card, parent, false);
        return new ListResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListResultsViewHolder holder, int position) {
        PostResultsModel post = posts.get(position);
        holder.title.setText(fromHtml(post.getTitle()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void setData(List<PostResultsModel> postResults) {
        this.posts.addAll(postResults);
    }

    @Override
    public void initialise(HomePresenter presenter) {

    }
}
