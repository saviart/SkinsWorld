package net.skinsworld;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import net.skinsworld.library.DatabaseHandler;
import net.skinsworld.library.GlobalVariables;
import net.skinsworld.library.UserFunctions;
import net.skinsworld.model.History;
import net.skinsworld.model.Item;
import net.skinsworld.model.Order;
import net.skinsworld.model.Recent;
import net.skinsworld.model.TopUser;
import net.skinsworld.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WebView_Login extends AppCompatActivity {
    String steam64_from_api;
    final String REALM_PARAM = "skinsworld";
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final WebView webView = new WebView(this);

        final Activity activity = this;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //checks the url being loaded
                Toast.makeText(WebView_Login.this, "Loaded !", Toast.LENGTH_SHORT).show();
                setTitle(url);
                Uri Url = Uri.parse(url);

                if(Url.getAuthority().equals(REALM_PARAM.toLowerCase())) {
                    Toast.makeText(WebView_Login.this, "Finished Loading !", Toast.LENGTH_SHORT).show();
                    // That means that authentication is finished and the url contains user's id.
                    webView.stopLoading();
                    // Extracts user id.
                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    steam64_from_api = userAccountUrl.getLastPathSegment();
                    new test().execute();
                }
                // Do whatever you want with the user's steam id
            }
        });
        setContentView(webView);

        // Constructing openid url request
        String url = "https://steamcommunity.com/openid/login?" +
                "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.mode=checkid_setup&" +
                "openid.ns=http://specs.openid.net/auth/2.0&" +
                "openid.realm=https://" + REALM_PARAM + "&" +
                "openid.return_to=https://" + REALM_PARAM + "/signin/";

        webView.loadUrl(url);

    }
    class test extends AsyncTask<String,String,String>{
        JSONObject playersObj;
        JSONObject jsonResult;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(WebView_Login.this);
            pd.setMessage("Loading...please wait !");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            pd.cancel();
            try {
                //Toast.makeText(WebView_Login.this, GlobalVariables.listItem.get(0).getGame(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WebView_Login.this, Activity_MainScreen.class));
                //Toast.makeText(WebView_Login.this, GlobalVariables.user.getSteamID64(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(WebView_Login.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                //lay thong tin user tu steam ve
                UserFunctions uf = new UserFunctions();
                playersObj = uf.getUserInfo(steam64_from_api).getJSONObject("response").getJSONArray("players").getJSONObject(0);
                //insert thong tin user, va lay thong tin app ve
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                db.resetTables();
                JSONObject json_registered = uf.signUp(playersObj.getString("steamid"),playersObj.getString("avatarmedium"),playersObj.getString("personaname"),GlobalVariables.gaid);
                Gson gson = new Gson();
                GlobalVariables.user = gson.fromJson(json_registered.getJSONArray("user").getJSONObject(0).toString(),User.class);
                db.addUser(GlobalVariables.user);
                GlobalVariables.listItem = new ArrayList<Item>();
                for (int i = 0;i<json_registered.getJSONArray("item").length();i++){
                    GlobalVariables.listItem.add(gson.fromJson(json_registered.getJSONArray("item").getJSONObject(i).toString(), Item.class));
                }
                GlobalVariables.listOrder = new ArrayList<>();
                for (int i = 0;i<json_registered.getJSONArray("order").length();i++){
                    GlobalVariables.listOrder.add(gson.fromJson(json_registered.getJSONArray("order").getJSONObject(i).toString(), Order.class));
                }
                //Log.v("----------v-------",json_registered.getString("totalInvited"));
                GlobalVariables.totalInvited = json_registered.getString("totalInvited");
                GlobalVariables.totalCoins = json_registered.getString("totalCoins");

                GlobalVariables.listHistory = new ArrayList<>();
                for (int i = 0;i<json_registered.getJSONArray("history").length();i++){
                    GlobalVariables.listHistory.add(gson.fromJson(json_registered.getJSONArray("history").getJSONObject(i).toString(), History.class));
                }
                GlobalVariables.listRecent = new ArrayList<>();
                JSONArray recentArray = json_registered.getJSONArray("recent");
                for (int i = 0; i < recentArray.length(); i++) {
                    try {
                        GlobalVariables.listRecent.add(gson.fromJson(recentArray.getJSONObject(i).toString(), Recent.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                GlobalVariables.listTop = new ArrayList<>();
                JSONArray topArray = json_registered.getJSONArray("topuser");
                for (int i = 0; i < topArray.length(); i++) {
                    try {
                        GlobalVariables.listTop.add(gson.fromJson(topArray.getJSONObject(i).toString(), TopUser.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //getURL = json_registered.getString("SteamID64");
            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }
    }
    class regsiter extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            //webview_login.setVisibility(View.INVISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            startActivity(new Intent(WebView_Login.this, Activity_MainScreen.class));
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //GlobalVariables.user = new User(jsHTML.getString("steamid64"),jsHTML.getString("avatar"),jsHTML.getString("personaname"));
//                UserFunctions uf = new UserFunctions();
//                JSONObject json_registered = uf.signUp(GlobalVariables.user, GlobalVariables.gaid);
//                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//                db.resetTables();
//                GlobalVariables.user.setId(json_registered.getString("UserID"));
//                GlobalVariables.user.setSteamid64(json_registered.getString("SteamID64"));
//                GlobalVariables.user.setTradeURL(json_registered.getString("TradeURL"));
//                GlobalVariables.user.setCoins(json_registered.getString("Coins"));
//                GlobalVariables.user.setCreated_date(json_registered.getString("CreatedDate"));
//                GlobalVariables.user.setActive(json_registered.getString("Active"));
//                GlobalVariables.user.setGaid(json_registered.getString("GAID"));
//                GlobalVariables.user.setInvited_by(json_registered.getString("InvitedBy"));
//                GlobalVariables.user.setAvatar(json_registered.getString("Avatar"));
//                GlobalVariables.user.setPersonaName(json_registered.getString("PersonaName"));
//                db.addUser(GlobalVariables.user);
//                startActivity(new Intent(WebView_Login.this, Activity_MainScreen.class));

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}