package com.example.tcashapps.fragment;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tcashapps.R;
import com.example.tcashapps.activity.DetailBlogActivity;
import com.example.tcashapps.adapter.VideoAdapter;
import com.example.tcashapps.model.retrofit.APIClient;
import com.example.tcashapps.model.retrofit.APIService;
import com.example.tcashapps.model.retrofit.Content;
import com.example.tcashapps.model.retrofit.ContentResponse;
import com.example.tcashapps.model.room.ContentViewModel;
import com.example.tcashapps.service.DataService;

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
public class VideoFragment extends Fragment {
    @BindView(R.id.rvVideo)
    RecyclerView rvVideo;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.btnDeleteAll)
    Button btnDeleteAll;

    ProgressDialog progressDialog;

    APIService apiService;
//    List<Content> list = new ArrayList<>();

    VideoAdapter adapter;
    ContentViewModel contentViewModel;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);

        initRecylerview();

        apiService = APIClient.getClient().create(APIService.class);
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);
        contentViewModel.getAllVideoContent().observe(this, new Observer<List<Content>>() {
            @Override
            public void onChanged(@Nullable List<Content> contents) {
                adapter.setContent(contents);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                loadData();
                getActivity().startService(new Intent(getActivity(), DataService.class));
                refreshLayout.setRefreshing(false);
            }
        });

//        initRecylerview();
//        loadData();
        return view;
    }

    private void loadData(){
        contentViewModel.getAllVideoContent();
        initRecylerview();
//        progressDialog();
//        Call<ContentResponse> call = apiService.getVideo();
//        call.enqueue(new Callback<ContentResponse>() {
//            @Override
//            public void onResponse(Call<ContentResponse> call, Response<ContentResponse> response) {
//                if (response.code() == 200){
//                    contentViewModel.deleteAllVideo();
//                    for (int i=0; i<response.body().getMessage().size(); i++){
//                        Content c = response.body().getMessage().get(i);
//                        contentViewModel.insert(c);
//                    }
//                    initRecylerview();
//                }
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ContentResponse> call, Throwable t) {
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//            }
//        });
    }

    private void initRecylerview(){
//        List blog = new ArrayList();
//        for (int i=1; i<=20; i++){
//            blog.add("Judul dari Video "+i);
//        }

//        VideoAdapter adapter = new VideoAdapter(list, 1);
        adapter = new VideoAdapter(1);
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVideo.setHasFixedSize(true);
        rvVideo.setAdapter(adapter);

        adapter.setOnItemClickListener(new VideoAdapter.onClikListener() {
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

    @OnClick(R.id.btnDeleteAll)
    public void deleteAll(){
//        List<Content> list = contentViewModel.getAllVideoContent().getValue();
//        String id = list.get(0).getId();
//        Content c = contentViewModel.getDetailContent(id);
        getActivity().startService(new Intent(getActivity(), DataService.class));
//        contentViewModel.deleteAllContens();
    }

    private void progressDialog(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

}
