package net.skinsworld;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import net.skinsworld.library.DatabaseHandler;
import net.skinsworld.library.GlobalVariables;
import net.skinsworld.library.JSONParser;
import net.skinsworld.library.UserFunctions;
import net.skinsworld.model.User;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WebView_Login extends AppCompatActivity {
    WebView webview_login;
    Boolean flag = false;
    String getURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.webview_login);
        webview_login = (WebView) findViewById(R.id.webview_site);
        webview_login.getSettings().setJavaScriptEnabled(true);
        webview_login.getSettings().setLoadsImagesAutomatically(true);
        webview_login.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview_login.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        webview_login.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                Log.v("----------------",url);
                view.loadUrl(url);
                if(url.contains("res.php")){
                    flag = true;
                    getURL = url;
                }
                Log.v("----flag----",flag.toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(flag){
                    webview_login.loadUrl("javascript:window.HTMLOUT.processHTML(document.getElementsByTagName('body')[0].innerHTML);");
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(WebView_Login.this);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
        webview_login.loadUrl("http://skinsworld.net/login/demo.php");



    }
    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html)
        {
            // process the html as needed by the app
            try {
                Log.v("-----------",html);
                JSONObject js = new JSONObject(html);
                GlobalVariables.user = new User(js.getString("steamid64"),js.getString("avatar"),js.getString("personaname"));
                //UserFunctions uf = new UserFunctions();
                //JSONObject json_registered = uf.signUp(GlobalVariables.user, GlobalVariables.gaid);
                //startActivity(new Intent(WebView_Login.this, Activity_MainScreen.class));
                new regsiter().execute();
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
                UserFunctions uf = new UserFunctions();
                JSONObject json_registered = uf.signUp(GlobalVariables.user, GlobalVariables.gaid);
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                db.resetTables();
                GlobalVariables.user.setId(json_registered.getString("UserID"));
                GlobalVariables.user.setSteamid64(json_registered.getString("SteamID64"));
                GlobalVariables.user.setTradeURL(json_registered.getString("TradeURL"));
                GlobalVariables.user.setCoins(json_registered.getString("Coins"));
                GlobalVariables.user.setCreated_date(json_registered.getString("CreatedDate"));
                GlobalVariables.user.setActive(json_registered.getString("Active"));
                GlobalVariables.user.setGaid(json_registered.getString("GAID"));
                GlobalVariables.user.setInvited_by(json_registered.getString("InvitedBy"));
                GlobalVariables.user.setAvatar(json_registered.getString("Avatar"));
                GlobalVariables.user.setPersonaName(json_registered.getString("PersonaName"));
                db.addUser(GlobalVariables.user);
                startActivity(new Intent(WebView_Login.this, Activity_MainScreen.class));

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
