package net.skinsworld.fragment_mainscreen;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import  android.view.View;
import  android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.skinsworld.R;
import net.skinsworld.adapter.Adapter_RcvEarn;
import net.skinsworld.library.GlobalVariables;
import net.skinsworld.library.UserFunctions;
import net.skinsworld.model.History;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Earn extends Fragment {
    View view;
    ImageView fyberwall;
    ImageView tapjoywall;
    ImageView sonicwall;
    ImageView fybervideo;
    ImageView tapjoyvideo;
    ImageView sonicvideo;
    ImageView btn_refresh_lastearning;
    TextView tv_missingcoins;
    ProgressDialog pd;

    RecyclerView ListItems;
    Adapter_RcvEarn adapter;

    public Fragment_Earn(){}


    @NonNull
    @Override
    public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStage) {
        view = inflater.inflate(R.layout.fragment_earn,container,false);
        anhxa();
        adapter = new Adapter_RcvEarn(getActivity(), GlobalVariables.listHistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ListItems.setLayoutManager(layoutManager);
        ListItems.setAdapter(adapter);
       // adapter.notifyDataSetChanged();
        fyberwall = (ImageView) view.findViewById(R.id.fyberwall);
        tapjoywall = (ImageView) view.findViewById(R.id.tapjoywall);
        sonicwall = (ImageView) view.findViewById(R.id.sonicwall);
        fybervideo = (ImageView) view.findViewById(R.id.fybervideo);
        tapjoyvideo = (ImageView) view.findViewById(R.id.tapjoyvideo);
        sonicvideo = (ImageView) view.findViewById(R.id.sonicvideo);

        click_missingcoins();
        click_btn_refresh_lastearning();
        click_sonicvideo();
        click_tapjoyvideo();
        click_fybervideo();
        click_sonicwall();
        click_tapjoywall();
        click_fyberwall();

        return view;
    }

    private void click_missingcoins() {
        tv_missingcoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());


                WebView wv = new WebView(getContext());
                wv.loadUrl(getResources().getString(R.string.help_missingcoins_url));
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);

                        return true;
                    }
                });

                alert.setView(wv);
                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alert.show();


            }
        });
    }

    private void click_btn_refresh_lastearning() {
        btn_refresh_lastearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new loadMyHistory().execute();

            }
        });
    }
    class loadMyHistory extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...please wait !");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            pd.cancel();
            adapter.setData(GlobalVariables.listHistory);
            adapter.notifyDataSetChanged();

        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            JSONObject historyObj = uf.loadMyHistory(GlobalVariables.user.getUserID());
            JSONArray historyArray = null;
            try {
                historyArray = historyObj.getJSONArray("history");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GlobalVariables.listHistory = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = 0; i < historyArray.length(); i++) {
                try {
                    GlobalVariables.listHistory.add(gson.fromJson(historyArray.getJSONObject(i).toString(), History.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    private void click_fyberwall() {
        fyberwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm Fyber wall",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_tapjoywall() {
        tapjoywall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm tapjoy wall",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_sonicwall() {
        sonicwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm sonic wall",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_fybervideo() {
        fybervideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm Fyber video",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_tapjoyvideo() {
        tapjoyvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm Tapjoy video",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_sonicvideo() {
        sonicvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm Sonic video",Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void anhxa() {
        tv_missingcoins = (TextView) view.findViewById(R.id.tv_missingcoins);
        btn_refresh_lastearning = (ImageView) view.findViewById(R.id.btn_refresh_lastearning);
        ListItems = (RecyclerView) view.findViewById(R.id.rvearnitems);

        //Thêm item từ database code ở đây, còn anh làm thế nào gọi được database thì kệ mẹ anh =))))))


    }

}
