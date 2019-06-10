package net.skinsworld.fragment_mainscreen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


import net.skinsworld.R;

public class Activity_Login2Device extends AppCompatActivity {
    ImageView tv_fbchatcontact;
    ImageView tv_twittercontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_login2device);
        tv_fbchatcontact = (ImageView) findViewById(R.id.tv_fbchatcontact);
        tv_fbchatcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://m.me/SkinsWorld.net")));
            }
        });
        tv_twittercontact = (ImageView) findViewById(R.id.tv_twittercontact);
        tv_twittercontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getOpenTwitterIntent(getApplication(), "SkinsWorldApp");
                startActivity(i);
            }
        });
    }

    public static Intent getOpenTwitterIntent(Context c, String Username) {

        try {
            c.getPackageManager().getPackageInfo("com.twitter.android", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + Username));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + Username));
        }

    }
}
