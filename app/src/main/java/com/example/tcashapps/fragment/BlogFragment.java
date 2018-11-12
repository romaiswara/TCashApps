package com.example.tcashapps.fragment;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tcashapps.R;
import com.example.tcashapps.activity.DetailBlogActivity;
import com.example.tcashapps.adapter.BlogAdapter;
import com.example.tcashapps.model.retrofit.APIClient;
import com.example.tcashapps.model.retrofit.APIService;
import com.example.tcashapps.model.retrofit.Content;
import com.example.tcashapps.model.retrofit.ContentResponse;
import com.example.tcashapps.model.room.ContentViewModel;
import com.example.tcashapps.service.DataService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {
    @BindView(R.id.rvBlog)
    RecyclerView rvBlog;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout refreshLayout;

    APIService apiService;
    BlogAdapter adapter;
    ContentViewModel contentViewModel;

    ProgressDialog progressDialog;

    public static String URL = "URL_";
    public static String TITLE = "_TITLE";
    public static String COVER = "_COVER";

    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        ButterKnife.bind(this, view);

        initRecylerview();

        apiService = APIClient.getClient().create(APIService.class);
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);
        contentViewModel.getAllBlogContent().observe(this, new Observer<List<Content>>() {
            @Override
            public void onChanged(@Nullable List<Content> contents) {
                adapter.setContent(contents);
            }
        });
//        loadData();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().startService(new Intent(getActivity(), DataService.class));
                refreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private void loadData(){
        contentViewModel.getAllBlogContent();
        initRecylerview();
//        progressDialog();
//        Call<ContentResponse> call = apiService.getBlog();
//        call.enqueue(new Callback<ContentResponse>() {
//            @Override
//            public void onResponse(Call<ContentResponse> call, Response<ContentResponse> response) {
//                if (response.code() == 200){
//                    contentViewModel.deleteAllBlog();
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
        adapter = new BlogAdapter(1);
        rvBlog.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvBlog.setHasFixedSize(true);
        rvBlog.setAdapter(adapter);

        adapter.setOnItemClickListener(new BlogAdapter.onClikListener() {
            @Override
            public void onClickItem(Content content) {
                Bundle bundle = new Bundle();
                bundle.putString(TITLE, content.getTitle());
                bundle.putString(URL, content.getContent());
                bundle.putString(COVER, content.getThumbnails());
                getActivity().startActivity(new Intent(getActivity(), DetailBlogActivity.class).putExtras(bundle));
//                getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://stackoverflow.com/")));
            }
        });
    }

    private void progressDialog(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }
}
