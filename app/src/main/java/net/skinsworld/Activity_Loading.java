package net.skinsworld;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;

import net.skinsworld.library.DatabaseHandler;
import net.skinsworld.library.GlobalVariables;
import net.skinsworld.library.UserFunctions;
import net.skinsworld.library.Util;
import net.skinsworld.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity_Loading extends AppCompatActivity {

    DatabaseHandler db;

    Boolean isDeviceRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_load);
        db = new DatabaseHandler(Activity_Loading.this);
        new check().execute();


    }

    class check extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            new getUser().execute();
//            if(db.getRowCount()>0){
//                //đã có user
//                //giờ lấy userid và get trên steam về
//                // goi asynctask va get info tren steam ve
//                GlobalVariables.user = db.getUser();
//                //load data roi pass vao giao dien
//
//                //startActivity(new Intent(Activity_Loading.this, Activity_Signup.class));
//
//            }else{
//                //sang register
//                startActivity(new Intent(Activity_Loading.this, Activity_Signup.class));
//            }
        }

        @Override
        protected String doInBackground(String... strings) {
            GlobalVariables.gaid = Util.getGoogleAdvertisingID(Activity_Loading.this);
            return null;
        }
    }

    class getUser extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            if (isDeviceRegistered) {
                startActivity(new Intent(Activity_Loading.this, Activity_MainScreen.class));
            } else {
                startActivity(new Intent(Activity_Loading.this, Activity_Signup.class));
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            Gson gson = new Gson();
            try {
                JSONObject json_user = uf.getUserByGAID(GlobalVariables.gaid);
                GlobalVariables.user = gson.fromJson(json_user.toString(), User.class);
                if (GlobalVariables.user != null) {
                    isDeviceRegistered = true;
                }
            } catch (Exception ee) {
                //user ko ton tai voi gaid nay
                isDeviceRegistered = false;
            }
            if (isDeviceRegistered) {
                try {
                    JSONObject js_user_from_steam = uf.getUserInfo(GlobalVariables.user.getSteamID64());
                    JSONObject playersObj = js_user_from_steam.getJSONObject("response").getJSONArray("players").getJSONObject(0);
                    //insert thong tin user, va lay thong tin app ve
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.resetTables();
                    JSONObject json_registered = uf.signUp(playersObj.getString("steamid"), playersObj.getString("avatarmedium"), playersObj.getString("personaname"), GlobalVariables.gaid);
                    GlobalVariables.user = gson.fromJson(json_registered.toString(), User.class);
                    db.addUser(GlobalVariables.user);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
}