package net.skinsworld;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import net.skinsworld.library.DatabaseHandler;
import net.skinsworld.library.GlobalVariables;
import net.skinsworld.library.UserFunctions;
import net.skinsworld.model.User;

import org.json.JSONObject;

public class WebView_Login extends AppCompatActivity {
    WebView webview_login;
    Boolean flag = false;
    String getURL;
    String steam64_from_api;
    final String REALM_PARAM = "skinsworld.net";
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //checks the url being loaded
                setTitle(url);
                Uri Url = Uri.parse(url);

                if(Url.getAuthority().equals(REALM_PARAM.toLowerCase())) {
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
                startActivity(new Intent(WebView_Login.this, Activity_MainScreen.class));
                //Toast.makeText(WebView_Login.this, GlobalVariables.user.getSteamID64(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
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
                GlobalVariables.user = gson.fromJson(json_registered.toString(),User.class);
                db.addUser(GlobalVariables.user);
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