package uk.me.paulriley.typicodeclient.view.home;

import java.util.ArrayList;

import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;

public interface HomeView {
    void updateData(ArrayList<PostResultsModel> postResults);
}
