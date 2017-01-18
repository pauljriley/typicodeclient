package uk.me.paulriley.typicodeclient.view.detail;

import java.util.ArrayList;

import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;

interface DetailView {
    void updateData(ArrayList<CommentResultsModel> postComments);
}
