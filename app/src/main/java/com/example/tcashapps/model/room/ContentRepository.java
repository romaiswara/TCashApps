package com.example.tcashapps.model.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.tcashapps.model.retrofit.Content;

import java.util.List;

public class ContentRepository {
    private ContentDAO contentDAO;
    private LiveData<List<Content>> allContent;

    public ContentRepository(Application application) {
        ContentDatabase db = ContentDatabase.getInstance(application);
        contentDAO = db.contentDAO();
        allContent = contentDAO.getAllContents();
    }

    public void insert (Content content){
        new InsertContentAsyncTask(contentDAO).execute(content);
    }

    public void update (Content content){
        new UpdateContentAsynTask(contentDAO).execute(content);
    }

    public void delete (Content content){
        new DeleteContentAsynTask(contentDAO).execute(content);
    }

    public void deleteAllContents(){
        new DeleteAllContentAsynTask(contentDAO).execute();
    }

    public LiveData<List<Content>> getAllContents(){
        return allContent;
    }

    public LiveData<List<Content>> getAllBlogContent(){
        return contentDAO.getAllBlog();
    }

    public LiveData<List<Content>> getAllVideoContent(){
        return contentDAO.getAllVideo();
    }

    public LiveData<List<Content>> getDetailContent(String id){
        return contentDAO.getDetailContent(id);
    }

    private static class InsertContentAsyncTask extends AsyncTask<Content, Void, Void>{
        private ContentDAO contentDAO;

        public InsertContentAsyncTask(ContentDAO contentDAO) {
            this.contentDAO = contentDAO;
        }

        @Override
        protected Void doInBackground(Content... contents) {
            contentDAO.insert(contents[0]);
            return null;
        }
    }

    private static class UpdateContentAsynTask extends AsyncTask<Content, Void, Void>{
        private ContentDAO contentDAO;

        public UpdateContentAsynTask(ContentDAO contentDAO) {
            this.contentDAO = contentDAO;
        }

        @Override
        protected Void doInBackground(Content... contents) {
            contentDAO.update(contents[0]);
            return null;
        }
    }

    private static class DeleteContentAsynTask extends AsyncTask<Content, Void, Void>{
        private ContentDAO contentDAO;

        public DeleteContentAsynTask(ContentDAO contentDAO) {
            this.contentDAO = contentDAO;
        }

        @Override
        protected Void doInBackground(Content... contents) {
            contentDAO.delete(contents[0]);
            return null;
        }
    }

    private static class DeleteAllContentAsynTask extends AsyncTask<Void, Void, Void>{
        private ContentDAO contentDAO;

        public DeleteAllContentAsynTask(ContentDAO contentDAO) {
            this.contentDAO = contentDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contentDAO.deleteAllNotes();
            return null;
        }
    }

//    private static class DetailContentAsynTask extends AsyncTask<String, Void, Void>{
//        private ContentDAO contentDAO;
//
//        public DetailContentAsynTask(ContentDAO contentDAO) {
//            this.contentDAO = contentDAO;
//        }
//
//        @Override
//        protected Void doInBackground(String... strings) {
//            contentDAO.getDetailContent(strings[0]);
//            return null;
//        }
//    }
}
