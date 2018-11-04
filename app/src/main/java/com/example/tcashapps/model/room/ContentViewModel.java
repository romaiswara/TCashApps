package com.example.tcashapps.model.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.tcashapps.model.retrofit.Content;

import java.util.List;

public class ContentViewModel extends AndroidViewModel {
    private ContentRepository repository;
    private LiveData<List<Content>> allContents;

    public ContentViewModel(@NonNull Application application) {
        super(application);
        repository = new ContentRepository(application);
        allContents = repository.getAllContents();
    }

    public void insert (Content content){
        repository.insert(content);
    }

    public void update (Content content){
        repository.update(content);
    }

    public void delete (Content content){
        repository.delete(content);
    }

    public void deleteAllContens(){
        repository.deleteAllContents();
    }

    public void deleteAllBlog(){
        repository.deleteAllBlog();
    }

    public void deleteAllVideo(){
        repository.deleteAllVideo();
    }

    public LiveData<List<Content>> getAllContents(){
        return allContents;
    }

    public LiveData<List<Content>> getAllBlogContent(){
        return repository.getAllBlogContent();
    }

    public LiveData<List<Content>> getAllVideoContent(){
        return repository.getAllVideoContent();
    }

    public LiveData<List<Content>> get3BlogContent(){
        return repository.get3BlogContent();
    }

    public LiveData<List<Content>> get3VideoContent(){
        return repository.get3VideoContent();
    }

    public LiveData<List<Content>> getDetailContent(String id){
        return repository.getDetailContent(id);
    }

}
