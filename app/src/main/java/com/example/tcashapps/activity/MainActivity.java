package com.example.tcashapps.activity;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.tcashapps.R;
import com.example.tcashapps.SessionManagement;
import com.example.tcashapps.fragment.BlogFragment;
import com.example.tcashapps.fragment.HomeFragment;
import com.example.tcashapps.fragment.PaymentFragment;
import com.example.tcashapps.fragment.ProfileFragment;
import com.example.tcashapps.fragment.VideoFragment;
import com.example.tcashapps.model.retrofit.APIClient;
import com.example.tcashapps.model.retrofit.APIService;
import com.example.tcashapps.model.retrofit.Content;
import com.example.tcashapps.model.retrofit.ContentResponse;
import com.example.tcashapps.model.room.ContentViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private Fragment fragment;
    private BottomNavigationView navigation;

    private static final String TAG = "MainActivity";

    SessionManagement sessionManagement;
    APIService apiService;
    ContentViewModel contentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = APIClient.getClient().create(APIService.class);
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);

        loadBlogData();
        loadVideoData();

        addFragment(new HomeFragment());

        sessionManagement = new SessionManagement(this);

        frameLayout = (FrameLayout) findViewById(R.id.frameContain);
        navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(this);

        Log.d(TAG, "onCreate: cekToken "+sessionManagement.getToken());
    }

    private void addFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.frameContain, fragment, fragment.getTag())
//                .addToBackStack(fragment.getTag())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menuHome:
                fragment = new HomeFragment();
                addFragment(fragment);
                return true;
            case R.id.menuBlog:
                fragment = new BlogFragment();
                addFragment(fragment);
                return true;
            case R.id.menuVideo:
                fragment = new VideoFragment();
                addFragment(fragment);
                return true;
            case R.id.menuPayment:
                fragment = new PaymentFragment();
                addFragment(fragment);
                return true;
            case R.id.menuProfile:
                fragment = new ProfileFragment();
                addFragment(fragment);
                return true;
        }
        return false;
    }

    public void changeMenu(int menuId){
        navigation.setSelectedItemId(menuId);
    }

    @Override
    public void onBackPressed() {
        int selectedItemId = navigation.getSelectedItemId();
        if (R.id.menuHome != selectedItemId){
            navigation.setSelectedItemId(R.id.menuHome);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: cekToken "+sessionManagement.getToken());
    }

    private void loadBlogData(){
        Call<ContentResponse> call = apiService.getBlog();
        call.enqueue(new Callback<ContentResponse>() {
            @Override
            public void onResponse(Call<ContentResponse> call, Response<ContentResponse> response) {
                if (response.code() == 200){
                    contentViewModel.deleteAllBlog();
                    for (int i=0; i<response.body().getMessage().size(); i++){
                        Content c = response.body().getMessage().get(i);
                        contentViewModel.insert(c);
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
                    contentViewModel.deleteAllVideo();
                    for (int i=0; i<response.body().getMessage().size(); i++){
                        Content c = response.body().getMessage().get(i);
                        contentViewModel.insert(c);
                    }
                }
            }

            @Override
            public void onFailure(Call<ContentResponse> call, Throwable t) {
            }
        });
    }
}
