package net.skinsworld.fragment_mainscreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import net.skinsworld.Activity_MainScreen;
import net.skinsworld.Activity_Signup;
import net.skinsworld.R;
import net.skinsworld.WebView_Login;
import net.skinsworld.adapter.AdapterRcvProfile;

import net.skinsworld.library.DatabaseHandler;
import net.skinsworld.library.GlobalVariables;
import net.skinsworld.library.UserFunctions;
import net.skinsworld.model.Item;
import net.skinsworld.model.Model_Profile;
import net.skinsworld.model.Order;
import net.skinsworld.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Profile extends Fragment {
    View view;
    RecyclerView ListItems;
    ArrayList<Model_Profile> arrayListItems;
    AdapterRcvProfile adapter;
    Button btn_getmore;
    Button btn_invite;
    ImageView btn_dailyreward;
    Button btn_share;
    Button btn_tradeurl_help;

    ImageView ivAvatar;
    TextView tvUsername;
    TextView tvCoins;
    TextView tvJoinDate;
    EditText etTradeURL;
    TextView invitecode;
    ImageView btn_refresh_lastorder;
    EditText etCode;
    private SwipeRefreshLayout swipe_Fragment_Profile;

    ProgressDialog pd;
    Boolean isSwipeOK = false;
    Boolean isInputCodeOK = false;

    public Fragment_Profile() {
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStage) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        anhxa();
        adapter = new AdapterRcvProfile(getActivity(), arrayListItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        ListItems.setLayoutManager(layoutManager);
        ListItems.setAdapter(adapter);


        // adapter.notifyDataSetChanged();
        click_refresh_lastorder();
        clickbtn_tradeurl_help();
        clickbtn_getmore();
        clickbtn_invite();
        clickbtn_share();
        clickbtn_dailyreward();


        //set toan bo thong tin user vao giao dien
        setUserProfile();


        //// Swipe to reload data here
        swipe_Fragment_Profile = (SwipeRefreshLayout) view.findViewById(R.id.swipe_Fragment_Profile);
        swipe_Fragment_Profile.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_Fragment_Profile.setRefreshing(true);
                new doSwipe().execute();
            }
        });

        if (GlobalVariables.user.getInvitedBy() == null) {
            final Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.popup_input_invited);


            // set the custom dialog components - text, image and button
            etCode = dialog.findViewById(R.id.popup_invited_code);

            Button btnSubmit = dialog.findViewById(R.id.popup_invited_submit);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new submitCode().execute();
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
        new refreshOrder().execute();

        return view;
    }

    class doSwipe extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            swipe_Fragment_Profile.setRefreshing(false);
            if(isSwipeOK){
                setUserProfile();
                arrayListItems = new ArrayList<>();
                for (int i = 0; i < GlobalVariables.listOrder.size(); i++) {
                    Model_Profile m = new Model_Profile();
                    m.setOrder(GlobalVariables.listOrder.get(i));
                    for (Item item : GlobalVariables.listItem) {
                        if (item.getID().equals(GlobalVariables.listOrder.get(i).getItem_ID())) {
                            m.setItem(item);
                        }
                    }
                    arrayListItems.add(m);
                }
                adapter = new AdapterRcvProfile(getActivity(), arrayListItems);
                ListItems.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            Gson gson = new Gson();
            DatabaseHandler db = new DatabaseHandler(getActivity());
            try {
                db.resetTables();
                JSONObject json_registered = uf.signUp(GlobalVariables.user.getSteamID64(), GlobalVariables.user.getAvatar(), GlobalVariables.user.getPersonaName(), GlobalVariables.gaid);

                GlobalVariables.user = gson.fromJson(json_registered.getJSONArray("user").getJSONObject(0).toString(), User.class);
                db.addUser(GlobalVariables.user);
                GlobalVariables.listOrder = new ArrayList<>();
                for (int i = 0; i < json_registered.getJSONArray("order").length(); i++) {
                    GlobalVariables.listOrder.add(gson.fromJson(json_registered.getJSONArray("order").getJSONObject(i).toString(), Order.class));
                }
                isSwipeOK = true;
            } catch (JSONException e) {
                e.printStackTrace();
                isSwipeOK = false;
            }
            return null;
        }
    }

    private void click_refresh_lastorder() {
        btn_refresh_lastorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity().getApplicationContext(), "đã bấm refresh last order", Toast.LENGTH_SHORT).show();
                new refreshOrder().execute();
            }
        });
    }

    class refreshOrder extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            arrayListItems = new ArrayList<>();
            for (int i = 0; i < GlobalVariables.listOrder.size(); i++) {
                Model_Profile m = new Model_Profile();
                m.setOrder(GlobalVariables.listOrder.get(i));
                for (Item item : GlobalVariables.listItem) {
                    if (item.getID().equals(GlobalVariables.listOrder.get(i).getItem_ID())) {
                        m.setItem(item);
                    }
                }
                arrayListItems.add(m);
            }
            adapter = new AdapterRcvProfile(getActivity(), arrayListItems);
            ListItems.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            JSONObject js = uf.loadOrder(GlobalVariables.user.getUserID());
            Gson gson = new Gson();
            DatabaseHandler db = new DatabaseHandler(getActivity());
            try {
                GlobalVariables.user = gson.fromJson(js.getJSONArray("user").getJSONObject(0).toString(), User.class);
                db.addUser(GlobalVariables.user);
                GlobalVariables.listOrder = new ArrayList<>();
                for (int i = 0; i < js.getJSONArray("order").length(); i++) {
                    GlobalVariables.listOrder.add(gson.fromJson(js.getJSONArray("order").getJSONObject(i).toString(), Order.class));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void clickbtn_tradeurl_help() {
        btn_tradeurl_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());


                WebView wv = new WebView(getContext());
                wv.loadUrl(getResources().getString(R.string.help_tradeurl_url));
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

    class submitCode extends AsyncTask<String, String, String> {
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
            try {

                new doSwipe().execute();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            DatabaseHandler db = new DatabaseHandler(getActivity());
            JSONObject js = uf.setInvitedBy(GlobalVariables.user.getUserID(),etCode.getText().toString().trim());
            try {
                isInputCodeOK = js.getJSONObject("Success").getString("Message").equals("OK");
                GlobalVariables.user.setCoins(""+(Integer.parseInt(GlobalVariables.user.getCoins())+50));
            } catch (JSONException e) {
                e.printStackTrace();
                isInputCodeOK = false;
            }

            return null;
        }
    }

    private void setUserProfile() {
        //avatar
        Picasso.with(getActivity().getApplicationContext()).load(GlobalVariables.user.getAvatar()).into(ivAvatar);
        //name
        //created date
        //coins
        //trade url
        tvUsername.setText(GlobalVariables.user.getPersonaName());
        tvCoins.setText(GlobalVariables.user.getCoins());
        tvJoinDate.setText(GlobalVariables.user.getCreatedDate());
        etTradeURL.setText(GlobalVariables.user.getTradeURL());
    }


    private void clickbtn_dailyreward() {
        btn_dailyreward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog

                final BottomSheetDialog dailypoint = new BottomSheetDialog(getContext());

                dailypoint.setContentView(R.layout.popup_daily_reward_steamprofile);
                dailypoint.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);

                // set the custom dialog components - text, image and button


                dailypoint.show();
            }
        });
    }

    private void clickbtn_share() {
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "đã bấm share", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickbtn_invite() {
        btn_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog

                final Dialog dialog = new Dialog(getContext());
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.popup_invite);


                // set the custom dialog components - text, image and button
                final TextView popup_invite_code = (TextView) dialog.findViewById(R.id.popup_invite_code);
                popup_invite_code.setText("SW"+ (Long.parseLong(GlobalVariables.user.getSteamID64()) - Long.parseLong("76561197960265728")) );


                Button popup_invite_copyintivecode = (Button) dialog.findViewById(R.id.popup_invite_copyintivecode);
                Button popup_invite_shareonfacebook = (Button) dialog.findViewById(R.id.popup_invite_shareonfacebook);
                // if button is clicked, close the custom dialog
                popup_invite_copyintivecode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getActivity().getApplicationContext(), "đã copy code", Toast.LENGTH_SHORT).show();
                        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText(popup_invite_code.getText().toString(), popup_invite_code.getText().toString());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getActivity().getApplicationContext(), "Invited code copied !", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }


        });
    }

    private void clickbtn_getmore() {
        btn_getmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();
            }
        });
    }

    private void changeFragment() {
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.fragment_one, AFragment.newInstance());
//        transaction.commit();
        Activity_MainScreen.main.selectIndex(2);


    }


    private void anhxa() {
        btn_refresh_lastorder = (ImageView) view.findViewById(R.id.btn_refresh_lastorder);
        btn_tradeurl_help = (Button) view.findViewById(R.id.btn_tradeurl_help);
        btn_share = (Button) view.findViewById(R.id.btn_share);
        btn_dailyreward = (ImageView) view.findViewById(R.id.btn_dailyreward);
        btn_invite = (Button) view.findViewById(R.id.btn_invite);
        btn_getmore = (Button) view.findViewById(R.id.btn_getmore);
        ListItems = (RecyclerView) view.findViewById(R.id.rvtransitems);
        arrayListItems = new ArrayList<>();
        for (int i = 0; i < GlobalVariables.listOrder.size(); i++) {
            Model_Profile m = new Model_Profile();
            m.setOrder(GlobalVariables.listOrder.get(i));
            for (Item item : GlobalVariables.listItem) {
                if (item.getID().equals(GlobalVariables.listOrder.get(i).getItem_ID())) {
                    m.setItem(item);
                }
            }
            arrayListItems.add(m);
        }

        ivAvatar = view.findViewById(R.id.ivAvatar);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvCoins = view.findViewById(R.id.tvCoins);
        tvJoinDate = view.findViewById(R.id.tvJoinDate);
        etTradeURL = view.findViewById(R.id.etTradeURL);
        invitecode = view.findViewById(R.id.popup_invitecode);

        //Thêm item từ database code ở đây


    }

}





