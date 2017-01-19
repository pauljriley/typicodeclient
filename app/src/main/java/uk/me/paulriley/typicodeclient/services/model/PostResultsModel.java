package uk.me.paulriley.typicodeclient.services.model;

import java.io.Serializable;

public class PostResultsModel implements Serializable {
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostResultsModel(int userId, int Id, String title, String body) {
        this.userId = userId;
        this.id = Id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
