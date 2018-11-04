package com.example.tcashapps.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.tcashapps.Constant;
import com.example.tcashapps.R;
import com.example.tcashapps.activity.DetailBlogActivity;
import com.example.tcashapps.activity.MainActivity;
import com.example.tcashapps.adapter.BlogAdapter;
import com.example.tcashapps.adapter.VideoAdapter;
import com.example.tcashapps.model.retrofit.APIClient;
import com.example.tcashapps.model.retrofit.APIService;
import com.example.tcashapps.model.retrofit.Content;
import com.example.tcashapps.model.retrofit.ContentResponse;
import com.example.tcashapps.model.room.ContentViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tcashapps.fragment.BlogFragment.COVER;
import static com.example.tcashapps.fragment.BlogFragment.TITLE;
import static com.example.tcashapps.fragment.BlogFragment.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.slider)
    SliderLayout sliderLayout;
    @BindView(R.id.rvBlog)
    RecyclerView rvBlog;
    @BindView(R.id.rvVideo)
    RecyclerView rvVideo;

//    BlogAdapter adapterBlog;
    VideoAdapter adapterVideo, adapterBlog;
    Context context;

    APIService apiService;
    ContentViewModel contentViewModel;
    List<Content> listBlog, listVideo;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView(Constant.VIDEO);
        apiService = APIClient.getClient().create(APIService.class);
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);

        contentViewModel.get3BlogContent().observe(this, new Observer<List<Content>>() {
            @Override
            public void onChanged(@Nullable List<Content> contents) {
                adapterBlog.setContent(contents);
            }
        });

        contentViewModel.get3VideoContent().observe(this, new Observer<List<Content>>() {
            @Override
            public void onChanged(@Nullable List<Content> contents) {
                adapterVideo.setContent(contents);
            }
        });

        listBlog=new ArrayList<>();
        listVideo= new ArrayList<>();
        initBanner();
        initBlogData();
        initVideoData();
        return view;
    }

    private void initBanner(){
        TextSliderView banner1 = new TextSliderView(getActivity());
        banner1.description("Banner 1").image(R.drawable.banner1).setScaleType(BaseSliderView.ScaleType.Fit);
        TextSliderView banner2 = new TextSliderView(getActivity());
        banner2.description("Banner 2").image(R.drawable.banner2).setScaleType(BaseSliderView.ScaleType.Fit);
        TextSliderView banner3 = new TextSliderView(getActivity());
        banner3.description("Banner 3").image(R.drawable.banner3).setScaleType(BaseSliderView.ScaleType.Fit);

        sliderLayout.addSlider(banner1);
        sliderLayout.addSlider(banner2);
        sliderLayout.addSlider(banner3);
    }

    private void initBlogData(){
        contentViewModel.get3BlogContent();
        initRecyclerView(Constant.BLOG);
//        Call<ContentResponse> call = apiService.getBlog();
//        call.enqueue(new Callback<ContentResponse>() {
//            @Override
//            public void onResponse(Call<ContentResponse> call, Response<ContentResponse> response) {
//                if (response.code() == 200){
//                    for (int i=0; i<3; i++){
//                        Content c = response.body().getMessage().get(i);
////                        url = c.getContent();
////                        cover = c.getThumbnails();
////                        title = c.getTitle();
//                        listBlog.add(c);
//                    }
//                    initRecyclerView(Constant.BLOG);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ContentResponse> call, Throwable t) {
//
//            }
//        });
    }

    private void initVideoData(){
        contentViewModel.get3VideoContent();
        initRecyclerView(Constant.VIDEO);
//        Call<ContentResponse> callVideo = apiService.getVideo();
//        callVideo.enqueue(new Callback<ContentResponse>() {
//            @Override
//            public void onResponse(Call<ContentResponse> call, Response<ContentResponse> response) {
//                if (response.code() == 200){
//                    for (int i=0; i<3; i++){
//                        Content c = response.body().getMessage().get(i);
////                        url = c.getContent();
////                        cover = c.getThumbnails();
////                        title = c.getTitle();
//                        listVideo.add(c);
//                    }
//                    initRecyclerView(Constant.VIDEO);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ContentResponse> call, Throwable t) {
//
//            }
//        });

    }

    private void initRecyclerView(String type){
        if (type.equalsIgnoreCase(Constant.BLOG)) {
            adapterBlog = new VideoAdapter(0);
            rvBlog.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvBlog.setHasFixedSize(true);
            rvBlog.setAdapter(adapterBlog);

            adapterBlog.setOnItemClickListener(new VideoAdapter.onClikListener() {
                @Override
                public void onClickItem(Content content) {
                    Bundle bundle = new Bundle();
                    bundle.putString(TITLE, content.getTitle());
                    bundle.putString(URL, content.getContent());
                    bundle.putString(COVER, content.getThumbnails());
                    getActivity().startActivity(new Intent(getActivity(), DetailBlogActivity.class).putExtras(bundle));
                }
            });
        }
        else if (type.equalsIgnoreCase(Constant.VIDEO)) {

            adapterVideo = new VideoAdapter(0);
            rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvVideo.setHasFixedSize(true);
            rvVideo.setAdapter(adapterVideo);

            adapterVideo.setOnItemClickListener(new VideoAdapter.onClikListener() {
                @Override
                public void onClickItem(Content content) {
                    Bundle bundle = new Bundle();
                    bundle.putString(TITLE, content.getTitle());
                    bundle.putString(URL, content.getContent());
                    bundle.putString(COVER, content.getThumbnails());
                    getActivity().startActivity(new Intent(getActivity(), DetailBlogActivity.class).putExtras(bundle));
                }
            });
        }
    }

    @OnClick(R.id.btnBlog)
    public void goBlog(){
        ((MainActivity)getActivity()).changeMenu(R.id.menuBlog);
    }

    @OnClick(R.id.btnVideo)
    public void goVideo(){
        ((MainActivity)getActivity()).changeMenu(R.id.menuVideo);
    }

}
