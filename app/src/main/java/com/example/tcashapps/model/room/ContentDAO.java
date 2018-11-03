package com.example.tcashapps.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tcashapps.model.retrofit.Content;

import java.util.List;

@Dao
public interface ContentDAO {
    @Insert
    void insert(Content content);

    @Update
    void update(Content content);

    @Delete
    void delete(Content content);

    @Query("DELETE FROM content_table")
    void deleteAllNotes();

    @Query("SELECT * FROM content_table ORDER BY dateModified ASC")
    LiveData<List<Content>> getAllContents();

    @Query("SELECT * FROM CONTENT_TABLE WHERE type LIKE 'blog'")
    LiveData<List<Content>> getAllBlog();

    @Query("SELECT * FROM CONTENT_TABLE WHERE type LIKE 'video'")
    LiveData<List<Content>> getAllVideo();

    @Query("SELECT * FROM content_table WHERE id_content LIKE :idContent")
    LiveData<List<Content>> getDetailContent(String idContent);
}
