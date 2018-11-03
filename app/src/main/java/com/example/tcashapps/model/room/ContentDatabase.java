package com.example.tcashapps.model.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.tcashapps.model.retrofit.Content;

@Database(entities = {Content.class}, version = 1)
public abstract class ContentDatabase extends RoomDatabase {

    private static ContentDatabase instance;

    public abstract ContentDAO contentDAO();

    public static synchronized ContentDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ContentDatabase.class, "content_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private ContentDAO contentDAO;

        public PopulateDbAsyncTask(ContentDatabase contentDatabase) {
            this.contentDAO = contentDatabase.contentDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
