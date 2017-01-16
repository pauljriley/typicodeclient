package uk.me.paulriley.typicodeclient.view.home.homeList;

import java.util.List;

import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.view.home.HomePresenter;

public interface ListResultsAdapterView {
    void initialise(HomePresenter presenter);
    int getItemCount();
    void setData(List<PostResultsModel> postResults);
}
