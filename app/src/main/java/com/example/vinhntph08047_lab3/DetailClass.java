package com.example.vinhntph08047_lab3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class DetailClass {

    @SerializedName("id")
    private int id;
    @SerializedName("date")
    private String date;
    @SerializedName("modified")
    private String modified;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private TitleArticle title;
    @SerializedName("content")
    private ArticleContent content;

    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public class TitleArticle {
        @SerializedName("rendered")
        String rendered;
    }

    public class ArticleContent {
        @SerializedName("rendered")
        String rendered;
    }

    public DetailClass(int id, String date, String modified, String link, TitleArticle title, ArticleContent content) {
        this.id = id;
        this.date = date;
        this.modified = modified;
        this.link = link;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public TitleArticle getTitle() {
        return title;
    }

    public void setTitle(TitleArticle title) {
        this.title = title;
    }

    public ArticleContent getContent() {
        return content;
    }

    public void setContent(ArticleContent content) {
        this.content = content;
    }
}
