package net.skinsworld.fragment_mainscreen;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import  android.view.View;
import  android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import net.skinsworld.R;

import net.skinsworld.adapter.Adapter_RcvTopuser;

import net.skinsworld.adapter.Adapter_TransactionHistory;
import net.skinsworld.model.Model_TopUser;
import net.skinsworld.model.Model_TransactionHistory;

import java.util.ArrayList;

public class Fragment_Community extends Fragment {
    View view;
    RecyclerView ListItems1;
    RecyclerView ListItems2;
    ArrayList<Model_TransactionHistory> arrayListItems1;
    ArrayList<Model_TopUser> arrayListItems2;
    Adapter_TransactionHistory adapter1;
    Adapter_RcvTopuser adapter2;
    ImageView ic_refresh;
    private SwipeRefreshLayout swipe_Fragment_Community;

    public Fragment_Community(){
    }


    @NonNull
    @Override
    public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStage) {
        view = inflater.inflate(R.layout.fragment_community,container,false);
        anhxa();


        //// Swipe to reload data here
        swipe_Fragment_Community = (SwipeRefreshLayout) view.findViewById(R.id.swipe_Fragment_Community);
        swipe_Fragment_Community.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_Fragment_Community.setRefreshing(true);
                // goi async load data ve sau do set refreshing false;
                swipe_Fragment_Community.setRefreshing(false);
            }
        });





       //setup layout transactions history
        adapter1 = new Adapter_TransactionHistory(getActivity(), arrayListItems1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        ListItems1.setLayoutManager(layoutManager1);
        ListItems1.setAdapter(adapter1);



        //setup layout top usser
        adapter2 = new Adapter_RcvTopuser(getActivity(), arrayListItems2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        ListItems2.setLayoutManager(layoutManager2);
        ListItems2.setAdapter(adapter2);

        return view;
    }
    private void anhxa() {


        ListItems1 = (RecyclerView) view.findViewById(R.id.rvtransactionhistory);
        ListItems2 = (RecyclerView) view.findViewById(R.id.rvtopuser);
        arrayListItems1 = new ArrayList<>();
        arrayListItems2 = new ArrayList<>();

        arrayListItems2.add(new Model_TopUser(R.drawable.img_avt, "Quang Phụ Khoa", "19/2/2019", "4032"));
        arrayListItems2.add(new Model_TopUser(R.drawable.img_avt, "Quang Phụ Khoa", "19/2/2019", "4032"));
        arrayListItems2.add(new Model_TopUser(R.drawable.img_avt, "Quang Phụ Khoa", "19/2/2019", "4032"));
        arrayListItems2.add(new Model_TopUser(R.drawable.img_avt, "Quang Phụ Khoa", "19/2/2019", "4032"));

        arrayListItems1.add(new Model_TransactionHistory(R.drawable.img_avt, "Quang Phụ Khoa", "Skins Name", "30min ago"));
        arrayListItems1.add(new Model_TransactionHistory(R.drawable.img_avt, "Quang Phụ Khoa", "Skins Name", "30min ago"));
        arrayListItems1.add(new Model_TransactionHistory(R.drawable.img_avt, "Quang Phụ Khoa", "Skins Name", "30min ago"));
        arrayListItems1.add(new Model_TransactionHistory(R.drawable.img_avt, "Quang Phụ Khoa", "Skins Name", "30min ago"));
        arrayListItems1.add(new Model_TransactionHistory(R.drawable.img_avt, "Quang Phụ Khoa", "Skins Name", "30min ago"));

    }

}
