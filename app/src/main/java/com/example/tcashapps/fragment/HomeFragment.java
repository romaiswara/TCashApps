package com.example.tcashapps.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.tcashapps.R;
import com.example.tcashapps.activity.MainActivity;
import com.example.tcashapps.adapter.BlogAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    BlogAdapter adapterBlog, adapterVideo;
    List blog, video;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        blog = new ArrayList();
        video = new ArrayList();

        initBanner();
        initBlogVideo();
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

    private void initBlogVideo(){
        for (int i=1; i<=12; i++){
            blog.add("Judul dari Blog "+i);
        }

        for (int i=1; i<=10; i++){
            video.add("Judul dari Video "+i);
        }
        initRecyclerView();
    }

    private void initRecyclerView(){
        adapterBlog = new BlogAdapter(blog,0);
        rvBlog.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvBlog.setHasFixedSize(true);
//        rvBlog.setNestedScrollingEnabled(false);
        rvBlog.setAdapter(adapterBlog);

        adapterBlog.setOnItemClickListener(new BlogAdapter.onClikListener() {
            @Override
            public void onClickItem() {
//                Bundle bundle = new Bundle();
//                bundle.putString(TITLE, title);
//                bundle.putString(URL, url);
//                bundle.putString(COVER, cover);
//                getActivity().startActivity(new Intent(getActivity(), DetailBlogActivity.class).putExtras(bundle));
//                getActivity().startActivity(new Intent(getActivity(), DetailBlogActivity.class));
            }
        });

        adapterVideo = new BlogAdapter(video,0);
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVideo.setHasFixedSize(true);
        rvVideo.setAdapter(adapterVideo);
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
