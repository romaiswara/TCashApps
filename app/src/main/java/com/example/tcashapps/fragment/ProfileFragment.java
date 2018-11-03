package com.example.tcashapps.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.tcashapps.Constant;
import com.example.tcashapps.R;
import com.example.tcashapps.SessionManagement;
import com.example.tcashapps.activity.LoginActivity;
import com.example.tcashapps.model.retrofit.APIClient;
import com.example.tcashapps.model.retrofit.APIService;
import com.example.tcashapps.model.retrofit.User;
import com.example.tcashapps.model.retrofit.UserResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    @BindView(R.id.tvNama)
    TextView tvNama;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvLogout)
    TextView tvLogout;
//    @BindView(R.id.imgPp)
//    CircleImageView imgPp;
    @BindView(R.id.ivPp)
    ImageView imageView;
    APIService apiService;
    SessionManagement sessionManagement;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        sessionManagement = new SessionManagement(getActivity());
        apiService = APIClient.getClient().create(APIService.class);
        loadData();

        return view;
    }

    private void loadData(){
        Call<UserResponse> call = apiService.myProfile(sessionManagement.getToken());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200){
                    User user = response.body().getUser();
                    TextDrawable drawable = TextDrawable.builder()
                            .buildRound(String.valueOf(user.getName().charAt(0)), Color.RED);
                    imageView.setImageDrawable(drawable);
                    tvNama.setText(user.getName());
                    tvPhone.setText(user.getPhone());
                    tvEmail.setText(user.getEmail());
                } else {
                    Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.tvLogout)
    public void logOut(){
        sessionManagement.setToken("");
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

}
