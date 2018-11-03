package com.example.tcashapps.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentResponse {
    @SerializedName("auth")
    @Expose
    private Boolean auth;
    @SerializedName("message")
    @Expose
    private List<Content> content = null;

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public List<Content> getMessage() {
        return content;
    }

    public void setMessage(List<Content> content) {
        this.content = content;
    }
}
