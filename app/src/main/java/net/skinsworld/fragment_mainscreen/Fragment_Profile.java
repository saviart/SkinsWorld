package net.skinsworld.fragment_mainscreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.view.inputmethod.InputMethodManager;
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
import net.skinsworld.R;
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
    Button btnSetTradeURL;
    ImageView ivAvatar;
    TextView tvUsername;
    TextView tvCoins;
    TextView tvJoinDate;
    EditText etTradeURL;
    TextView tvInviteCode;
    ImageView btn_refresh_lastorder;
    TextView tvTotalInvited;
    TextView tvTotalCoins;
    EditText etCode;
    private SwipeRefreshLayout swipe_Fragment_Profile;

    ProgressDialog pd;
    Boolean isSwipeOK = false;
    Boolean isInputCodeOK = false;
    Boolean canTakeDailyCoins = false;
    Boolean isSetTradeURLOK = false;

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
        clickSetURL();


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

                    //validate
                    String inputedCode = etCode.getText().toString().trim();
                    Long inputedSteamID64 = Long.parseLong(inputedCode.substring(2)) + Long.parseLong("76561197960265728");
                    String SteamID64Inputed = inputedSteamID64.toString();
                    if (inputedCode.equals("") || (!(inputedCode.toUpperCase().contains("SW"))) || (inputedCode.length() != 11)) {
                        Toast.makeText(getActivity(), "You must input correct invitation code format !", Toast.LENGTH_SHORT).show();
                    } else if (SteamID64Inputed.equals(GlobalVariables.user.getSteamID64())) {
                        Toast.makeText(getActivity(), "You cannot input your own invitation code !", Toast.LENGTH_SHORT).show();
                    } else {
                        new submitCode().execute();
                        dialog.dismiss();
                    }


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
            if (isSwipeOK) {
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
                GlobalVariables.totalInvited = json_registered.getString("totalInvited");
                GlobalVariables.totalCoins = json_registered.getString("totalCoins");
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

    private void clickSetURL() {
        btnSetTradeURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate
                if ((etTradeURL.getText().toString().trim().equals("")) || (!(etTradeURL.getText().toString().contains("https://steamcommunity.com/tradeoffer")))) {
                    Toast.makeText(getActivity(), "You must input correct Trade URL format !", Toast.LENGTH_SHORT).show();
                } else {
                    new setURL().execute();
                }

            }
        });
    }

    class setURL extends AsyncTask<String, String, String> {
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
            if (isSetTradeURLOK) {
                etTradeURL.setText(GlobalVariables.user.getTradeURL());
                Toast.makeText(getActivity(), "Done !", Toast.LENGTH_LONG).show();
                InputMethodManager inputManager =
                        (InputMethodManager) getActivity().
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(
                        getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } else
                Toast.makeText(getActivity(), "Error occurred ! Please try again later !", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            DatabaseHandler db = new DatabaseHandler(getActivity());
            JSONObject js = uf.setTradeURL(GlobalVariables.user.getUserID(), etTradeURL.getText().toString().trim());
            try {
                isSetTradeURLOK = js.getJSONObject("Success").getString("Message").equals("OK");
                Gson gson = new Gson();
                GlobalVariables.user = gson.fromJson(js.getJSONArray("user").getJSONObject(0).toString(), User.class);
                db.addUser(GlobalVariables.user);
            } catch (JSONException e) {
                e.printStackTrace();
                isSetTradeURLOK = false;
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
                if (isInputCodeOK) {
                    Toast.makeText(getActivity(), "OK !", Toast.LENGTH_SHORT).show();
                    new doSwipe().execute();
                } else {
                    Toast.makeText(getActivity(), "Invitation code unavailable !", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            DatabaseHandler db = new DatabaseHandler(getActivity());
            JSONObject js = uf.setInvitedBy(GlobalVariables.user.getUserID(), etCode.getText().toString().trim());
            try {
                if (js.getJSONObject("Success").getString("Message").equals("OK")) {
                    GlobalVariables.user.setCoins("" + (Integer.parseInt(GlobalVariables.user.getCoins()) + 50));
                    isInputCodeOK = true;
                }
                if (js.getJSONObject("Success").getString("Message").equals("No user !")) {
                    isInputCodeOK = false;
                }

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
        tvTotalCoins.setText(GlobalVariables.totalCoins == null ? "0" : GlobalVariables.totalCoins);
        tvTotalInvited.setText(GlobalVariables.totalInvited);
    }


    private void clickbtn_dailyreward() {
        btn_dailyreward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog


                String prefix = "SkinsWorld.net";
                if (GlobalVariables.user.getPersonaName().toUpperCase().contains(prefix.toUpperCase())) {
                    //đã có prefix ở tên trong steam
                    //if chưa nhận
                    new getDailyCoins().execute();

                } else {
                    BottomSheetDialog dailypoint = new BottomSheetDialog(getContext());
                    dailypoint.setContentView(R.layout.popup_daily_reward_steamprofile);
                    dailypoint.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
                    dailypoint.show();
                }


                // set the custom dialog components - text, image and button


            }
        });
    }

    class getDailyCoins extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            if (canTakeDailyCoins) {
                //cong coins
                BottomSheetDialog dailypoint = new BottomSheetDialog(getActivity());
                dailypoint.setContentView(R.layout.popup_daily_reward);
                dailypoint.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
                dailypoint.show();

            } else {
                BottomSheetDialog dailypoint = new BottomSheetDialog(getActivity());
                dailypoint.setContentView(R.layout.popup_daily_rewarded);
                dailypoint.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
                dailypoint.show();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            JSONObject js = uf.getDailyCoins(GlobalVariables.user.getUserID());
            try {
                canTakeDailyCoins = js.getString("Error").equals("0");
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                canTakeDailyCoins = false;
            }
            return null;
        }
    }

    private void clickbtn_share() {
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getActivity());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
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
                popup_invite_code.setText("SW" + (Long.parseLong(GlobalVariables.user.getSteamID64()) - Long.parseLong("76561197960265728")));


                Button popup_invite_copyintivecode = (Button) dialog.findViewById(R.id.popup_invite_copyintivecode);

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
        tvTotalInvited = view.findViewById(R.id.tvTotalInvited);
        tvTotalCoins = view.findViewById(R.id.tvTotalCoins);
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
        tvTotalInvited.setText(GlobalVariables.totalInvited);
        tvTotalCoins.setText(GlobalVariables.totalCoins == null ? "0" : GlobalVariables.totalCoins);
        ivAvatar = view.findViewById(R.id.ivAvatar);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvCoins = view.findViewById(R.id.tvCoins);
        tvJoinDate = view.findViewById(R.id.tvJoinDate);
        etTradeURL = view.findViewById(R.id.etTradeURL);
        tvInviteCode = view.findViewById(R.id.popup_invitecode);
        btnSetTradeURL = view.findViewById(R.id.btn_seturl);

        //Thêm item từ database code ở đây


    }


    public static String FACEBOOK_URL = "https://www.facebook.com/SkinsWorld.net";
    public static String FACEBOOK_PAGE_ID = "399485364185657";

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
}





