package com.example.tcashapps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tcashapps.R;
import com.example.tcashapps.model.retrofit.Content;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    List<Content> content = new ArrayList<>();
    int stts;

    onClikListener listener;

    public VideoAdapter(int stts) {
//        this.content = content;
        this.stts = stts;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Content c = content.get(i);
        if (c.getThumbnails() != null){
            viewHolder.ivBlog.setBackgroundResource(0);
        }
        viewHolder.bindItem(c);

//        if (i==2 || i==6 || i==7){
//            viewHolder.ivPlay.setVisibility(View.VISIBLE);
//        }
//        viewHolder.tvTitle.setText(c.getTitle());
    }

    @Override
    public int getItemCount() {
        if (stts == 0) {
            return 12;
        } else {
            return content.size();
        }
    }

    public void setContent(List<Content> content){
        this.content = content;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDeskripsi)
        TextView tvDeskripsi;
        @BindView(R.id.ivBlog)
        ImageView ivBlog;
        @BindView(R.id.ivPlay)
        ImageView ivPlay;

        Context context;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener!=null && position != RecyclerView.NO_POSITION) {
                        listener.onClickItem();
                    }
                }
            });
        }

        public void bindItem(Content content){
            tvTitle.setText(content.getTitle());
            tvDeskripsi.setText(content.getDescription());
            Picasso.with(context).load(content.getThumbnails()).into(ivBlog);
        }
    }

    public interface onClikListener{
        void onClickItem();
    }
    public void setOnItemClickListener(onClikListener listener){
        this.listener = listener;
    }


}
