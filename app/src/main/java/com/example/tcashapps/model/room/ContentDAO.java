package com.example.tcashapps.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tcashapps.model.retrofit.Content;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ContentDAO {
    @Insert(onConflict = REPLACE)
    void insert(Content content);

    @Update
    void update(Content content);

    @Delete
    void delete(Content content);

    @Query("DELETE FROM content_table")
    void deleteAllContent();

    @Query("DELETE FROM content_table WHERE type LIKE 'blog'")
    void deleteAllBlog();

    @Query("DELETE FROM content_table WHERE type LIKE 'video'")
    void deleteAllVideo();

    @Query("SELECT * FROM content_table ORDER BY dateModified ASC")
    LiveData<List<Content>> getAllContents();

    @Query("SELECT * FROM CONTENT_TABLE WHERE type LIKE 'blog' ORDER BY dateModified DESC")
    LiveData<List<Content>> getAllBlog();

    @Query("SELECT * FROM CONTENT_TABLE WHERE type LIKE 'video' ORDER BY dateModified DESC")
    LiveData<List<Content>> getAllVideo();

    @Query("SELECT * FROM CONTENT_TABLE WHERE type LIKE 'blog' ORDER BY dateModified DESC LIMIT 3")
    LiveData<List<Content>> get3Blog();

    @Query("SELECT * FROM CONTENT_TABLE WHERE type LIKE 'video' ORDER BY dateModified DESC LIMIT 3")
    LiveData<List<Content>> get3Video();

    @Query("SELECT * FROM content_table WHERE id_content LIKE :idContent")
    Content getDetailContent(String idContent);
}
