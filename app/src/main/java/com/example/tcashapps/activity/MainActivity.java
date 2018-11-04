package com.example.tcashapps.activity;

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

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private Fragment fragment;
    private BottomNavigationView navigation;

    private static final String TAG = "MainActivity";

    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
