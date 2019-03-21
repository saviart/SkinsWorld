package net.skinsworld.library;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import java.util.Random;


public class Util {

	public static String getGoogleAdvertisingID(Context context){
		try{
			return AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
		}catch (Exception e){
			Log.d("4444444444444444",e.getMessage());
			return "errOnGettingGoogleAdvertisingID";
		}

	}
	public final static int generateRandomNumber() {
			Random ran = new Random();
			int x = ran.nextInt(1999999999);
		return x;
	}

	public static final boolean isEmpty(EditText editText) {
		String target = editText.getText().toString().trim();
		return TextUtils.isEmpty(target);
	}
	public static final boolean isEmpty(TextView textview) {
		String target = textview.getText().toString().trim();
		return TextUtils.isEmpty(target);
	}
	
	public static boolean checkInternetConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = cm
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetwork != null && wifiNetwork.isConnected()) {
			return true;
		}

		NetworkInfo mobileNetwork = cm
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobileNetwork != null && mobileNetwork.isConnected()) {
			return true;
		}

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected()) {
			return true;
		}

		return false;
	}

}
