package com.example.tcashapps.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.tcashapps.model.retrofit.APIClient;
import com.example.tcashapps.model.retrofit.APIService;
import com.example.tcashapps.model.retrofit.Content;
import com.example.tcashapps.model.retrofit.ContentResponse;
import com.example.tcashapps.model.room.ContentViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataService extends Service {
    private APIService apiService;
    private ContentViewModel contentViewModel;

    public DataService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("AREMAVOICE", "onStartCommand: ");
        apiService = APIClient.getClient().create(APIService.class);
//        contentViewModel = ViewModelProviders.of(getApplicationContext()).get(ContentViewModel.class);
        contentViewModel = new ContentViewModel(getApplication());
        loadBlogData();
        loadVideoData();

        return super.onStartCommand(intent, flags, startId);
    }


    private void loadBlogData(){
        Call<ContentResponse> call = apiService.getBlog();
        call.enqueue(new Callback<ContentResponse>() {
            @Override
            public void onResponse(Call<ContentResponse> call, Response<ContentResponse> response) {
                if (response.code() == 200){
                    for (int i=0; i<response.body().getMessage().size(); i++){
                        Content c = response.body().getMessage().get(i);
                        contentViewModel.insert(c);
//                        if (contentViewModel.getDetailContent(c.getId()).size() == 0) {
//                            Log.d("AREMAVOICE", "onResponse: Blog null");
//                            contentViewModel.insert(c);
//                        } else {
//                            Log.d("AREMAVOICE", "onResponse: Blog !null");
//                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ContentResponse> call, Throwable t) {
            }
        });
    }

    private void loadVideoData(){
        Call<ContentResponse> call = apiService.getVideo();
        call.enqueue(new Callback<ContentResponse>() {
            @Override
            public void onResponse(Call<ContentResponse> call, Response<ContentResponse> response) {
                if (response.code() == 200){
                    for (int i=0; i<response.body().getMessage().size(); i++){
                        Content c = response.body().getMessage().get(i);
                        contentViewModel.insert(c);
//                        if (contentViewModel.getDetailContent(c.getId()).size() == 0) {
//                            Log.d("AREMAVOICE", "onResponse: Video null");
//                            contentViewModel.insert(c);
//                        } else {
//                            Log.d("AREMAVOICE", "onResponse: Video !null");
//                        }
                    }
                    stopSelf();
                }
            }

            @Override
            public void onFailure(Call<ContentResponse> call, Throwable t) {
                stopSelf();
            }
        });
    }

    @Override
    public void onDestroy() {
        Log.d("AREMAVOICE", "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
