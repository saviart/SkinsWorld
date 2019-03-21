package net.skinsworld;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import net.skinsworld.library.DatabaseHandler;
import net.skinsworld.library.GlobalVariables;
import net.skinsworld.library.Util;

public class Activity_Loading extends AppCompatActivity {

    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_load);
        db = new DatabaseHandler(Activity_Loading.this);
        new check().execute();
        if(db.getRowCount()>0){
            //đã có user
            //giờ lấy userid và get trên steam về
            // goi asynctask va get info tren steam ve
            GlobalVariables.user = db.getUser();
            startActivity(new Intent(Activity_Loading.this, Activity_MainScreen.class));
        }else{
            //sang register
            startActivity(new Intent(Activity_Loading.this, Activity_Signup.class));
        }

    }
    class check extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            GlobalVariables.gaid= Util.getGoogleAdvertisingID(Activity_Loading.this);
            return null;
        }
    }
}
