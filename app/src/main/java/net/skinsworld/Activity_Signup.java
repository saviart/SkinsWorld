package net.skinsworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Signup extends AppCompatActivity {
    Button btn_signup;
    CheckBox cb1;
    CheckBox cb2;

    TextView tv_policy;
    TextView tv_conditions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_signup);


        btn_signup = (Button) findViewById(R.id.btnSignup);
        cb1 = (CheckBox) findViewById(R.id.checkBox);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        //tv_policy = (TextView) findViewById(R.id.tv_policy) ;
        //tv_conditions = (TextView) findViewById(R.id.tv_conditions) ;
        //String policy = "<font color=#ffffff>I accept</font> <font color=#FF4081>Pravicy Policy</font>";
        //tv_policy.setText(Html.fromHtml(policy));
        //String conditions= "<font color=#ffffff>I accept</font> <font color=#FF4081>Terms and Conditions</font>";
        //tv_conditions.setText(Html.fromHtml(conditions));


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked() && cb2.isChecked()){
                    //đồng ý, giờ đăng kí
                    startActivity(new Intent(Activity_Signup.this, WebView_Login.class));
                }else{
                    Toast.makeText(Activity_Signup.this, getResources().getString(R.string.dong_y_su_dung), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // click vào text pravicy policy
//        tv_policy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(Activity_Signup.this);
//                alert.setTitle("Privacy Policy");
//
//                WebView wv = new WebView(Activity_Signup.this);
//                wv.loadUrl("http://skinsworld.net/policy/privacy.html");
//                wv.setWebViewClient(new WebViewClient() {
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        view.loadUrl(url);
//
//                        return true;
//                    }
//                });
//
//                alert.setView(wv);
//                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                });
//                alert.show();
//            }
//        });



//        tv_conditions.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(Activity_Signup.this);
//                alert.setTitle("Terms and Conditions");
//
//                WebView wv = new WebView(Activity_Signup.this);
//                wv.loadUrl("http://skinsworld.net/policy/conditions.html");
//                wv.setWebViewClient(new WebViewClient() {
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        view.loadUrl(url);
//
//                        return true;
//                    }
//                });
//
//                alert.setView(wv);
//                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                });
//                alert.show();
//            }
//        });
    }
    class register extends AsyncTask<String,String,String>{
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
            return null;
        }
    }
}
