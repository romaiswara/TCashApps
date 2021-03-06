package com.example.tcashapps.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tcashapps.R;
import com.example.tcashapps.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {
    @BindView(R.id.rvMenu)
    RecyclerView rvMenu;
    List menuList = new ArrayList();

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        ButterKnife.bind(this, view);
        for (int i=0; i<6;i++){
            menuList.add(i);
        }

        MenuAdapter adapter = new MenuAdapter(menuList);
        rvMenu.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvMenu.setHasFixedSize(true);
        rvMenu.setAdapter(adapter);

        adapter.setMenuListener(new MenuAdapter.onClickMenuListener() {
            @Override
            public void onClickMenu(int pos) {
                menuItem(pos);
            }
        });

        return view;
    }

    private void menuItem(int pos){
        int a = pos+1;
        Toast.makeText(getContext(), "Menu "+a, Toast.LENGTH_SHORT).show();
    }

}
