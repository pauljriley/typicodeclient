package uk.me.paulriley.typicodeclient.view.home;

import java.util.List;

import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.view.home.homeList.ListResultsAdapterView;

public interface HomeView {
    void setAdapter(ListResultsAdapterView listResultsAdapter);
    void updateData(List<PostResultsModel> postResults);
}
