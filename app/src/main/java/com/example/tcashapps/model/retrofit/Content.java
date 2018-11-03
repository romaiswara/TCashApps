package com.example.tcashapps.model.retrofit;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "content_table")
public class Content {
    @SerializedName("_id")
    @Expose
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id_content")
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("dateModified")
    @Expose
    private String dateModified;
    @SerializedName("thumbnails")
    @Expose
    private String thumbnails;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Content(String id, String title, String description, String content, String date, String dateModified, String thumbnails, String type, Integer v) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
        this.dateModified = dateModified;
        this.thumbnails = thumbnails;
        this.type = type;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
