package net.skinsworld;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_UserBlocked extends AppCompatActivity {

    ImageView tv_blockfbchatcontact;
    ImageView tv_blocktwittercontact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_userblocked);
        Activity_MainScreen.AM.finish();
        Activity_Loading.AL.finish();

        tv_blockfbchatcontact = (ImageView) findViewById(R.id.tv_blockfbchatcontact);
        tv_blocktwittercontact = (ImageView) findViewById(R.id.tv_twittercontact);

        tv_blockfbchatcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://m.me/SkinsWorld.net")));
            }
        });

        tv_blocktwittercontact.setOnClickListener(new View.OnClickListener() {
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
