package net.skinsworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_UserBlocked extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userblocked);
        Activity_MainScreen.AM.finish();
        Activity_Loading.AL.finish();
    }
}
