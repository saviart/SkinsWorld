package net.skinsworld.fragment_mainscreen;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.google.gson.Gson;

import net.skinsworld.R;

import net.skinsworld.adapter.Adapter_RcvTopuser;

import net.skinsworld.adapter.Adapter_TransactionHistory;
import net.skinsworld.event.OnClickIteml_TopUser;
import net.skinsworld.library.GlobalVariables;
import net.skinsworld.library.UserFunctions;
import net.skinsworld.model.History;
import net.skinsworld.model.Recent;
import net.skinsworld.model.TopUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Community extends Fragment implements OnClickIteml_TopUser{
    View view;
    RecyclerView ListItems1;
    RecyclerView ListItems2;
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
                new loadRecentOrder().execute();

            }
        });





       //setup layout transactions history
        adapter1 = new Adapter_TransactionHistory(getActivity(), GlobalVariables.listRecent);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        ListItems1.setLayoutManager(layoutManager1);
        ListItems1.setAdapter(adapter1);



        //setup layout top usser
        adapter2 = new Adapter_RcvTopuser(getActivity(), GlobalVariables.listTop, this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        ListItems2.setLayoutManager(layoutManager2);
        ListItems2.setAdapter(adapter2);

        return view;
    }

    @Override
    public void onClickItemTopUser(TopUser data) {

        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("SW" + (Long.parseLong(data.getInvitationCode()) - Long.parseLong("76561197960265728")),"SW" + (Long.parseLong(data.getInvitationCode()) - Long.parseLong("76561197960265728")));
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getActivity().getApplicationContext(), "Invited code copied !" + "SW" + (Long.parseLong(data.getInvitationCode()) - Long.parseLong("76561197960265728")), Toast.LENGTH_SHORT).show();

    }

    class loadRecentOrder extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            swipe_Fragment_Community.setRefreshing(false);
            adapter1.setData(GlobalVariables.listRecent);
            adapter1.notifyDataSetChanged();
        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            JSONObject recentObj = uf.loadRecent();
            JSONArray recentArray = null;
            try {
                recentArray = recentObj.getJSONArray("recent");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GlobalVariables.listRecent = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = 0; i < recentArray.length(); i++) {
                try {
                    GlobalVariables.listRecent.add(gson.fromJson(recentArray.getJSONObject(i).toString(), Recent.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    private void anhxa() {


        ListItems1 = (RecyclerView) view.findViewById(R.id.rvtransactionhistory);
        ListItems2 = (RecyclerView) view.findViewById(R.id.rvtopuser);



    }

}
