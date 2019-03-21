package net.skinsworld.library;

import net.skinsworld.model.User;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class UserFunctions {
	
	private JSONParser jsonParser;

	public UserFunctions(){
		jsonParser = new JSONParser();
	}

	public JSONObject signUp(User user, String gaid)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.register_tag));
		params.add(new BasicNameValuePair("steamid64", user.getSteamid64()));
		params.add(new BasicNameValuePair("avatar", user.getAvatar()));
		params.add(new BasicNameValuePair("personaname", user.getPersonaName()));
		params.add(new BasicNameValuePair("gaid", gaid));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}



	public JSONObject getUser(String gaid){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.get_user_by_device_tag));
		params.add(new BasicNameValuePair("gaid", gaid));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject getCoinsByID(String id)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.get_coins_tag));
		params.add(new BasicNameValuePair("userid", id));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONArray loadHistory(String id)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.load_history_tag));
		params.add(new BasicNameValuePair("userid", id));
		JSONArray json = jsonParser.getArrayFromURL(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONArray loadOffers()
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("task", GlobalVariables.load_offers_tag));
		JSONArray json = jsonParser.getArrayFromURL(GlobalVariables.apiURL, params);
		return json;
	}


}
