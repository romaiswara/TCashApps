package com.example.tcashapps.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tcashapps.R;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    List menuList = new ArrayList();

    onClickMenuListener menuListener;

    public void setMenuListener(onClickMenuListener menuListener) {
        this.menuListener = menuListener;
    }

    public MenuAdapter(List menuList) {
        this.menuList = menuList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu, viewGroup, false);
        int height = viewGroup.getMeasuredHeight() / 3;
        view.setMinimumHeight(height);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindItem(i);
//        if (i==0){
//            viewHolder.ivIcon.setBackgroundResource(R.drawable.menu1);
//            viewHolder.tvMenu.setText("Menu 1");
//        } else if (i==1){
//            viewHolder.ivIcon.setBackgroundResource(R.drawable.menu2);
//            viewHolder.tvMenu.setText("Menu 2");
//        }else if (i==2){
//            viewHolder.ivIcon.setBackgroundResource(R.drawable.menu3);
//            viewHolder.tvMenu.setText("Menu 3");
//        }else if (i==3){
//            viewHolder.ivIcon.setBackgroundResource(R.drawable.menu4);
//            viewHolder.tvMenu.setText("Menu 4");
//        }else if (i==4){
//            viewHolder.ivIcon.setBackgroundResource(R.drawable.menu5);
//            viewHolder.tvMenu.setText("Menu 5");
//        }else if (i==5){
//            viewHolder.ivIcon.setBackgroundResource(R.drawable.menu6);
//            viewHolder.tvMenu.setText("Menu 6");
//        }

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        TextView tvMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenu = itemView.findViewById(R.id.tvMenu);
            ivIcon = itemView.findViewById(R.id.ivLogoMenu);
        }

        public void bindItem(int pos){
            switch (pos){
                case 0:
                    ivIcon.setBackgroundResource(R.drawable.menu1);
                    tvMenu.setText("Menu 1");
                    break;
                case 1:
                    ivIcon.setBackgroundResource(R.drawable.menu2);
                    tvMenu.setText("Menu 2");
                    break;
                case 2:
                    ivIcon.setBackgroundResource(R.drawable.menu3);
                    tvMenu.setText("Menu 3");
                    break;
                case 3:
                    ivIcon.setBackgroundResource(R.drawable.menu4);
                    tvMenu.setText("Menu 4");
                    break;
                case 4:
                    ivIcon.setBackgroundResource(R.drawable.menu5);
                    tvMenu.setText("Menu 5");
                    break;
                case 5:
                    ivIcon.setBackgroundResource(R.drawable.menu6);
                    tvMenu.setText("Menu 6");
                    break;
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (menuListener != null && pos != RecyclerView.NO_POSITION){
                        menuListener.onClickMenu(pos);
                    }
                }
            });

        }
    }

    public interface onClickMenuListener{
        void onClickMenu(int pos);
    }
}
