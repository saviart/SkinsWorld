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

	public JSONObject signUp(String steamid64, String avatar, String personaname, String gaid)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.login_with_steam_tag));
		params.add(new BasicNameValuePair("steamid64", steamid64));
		params.add(new BasicNameValuePair("avatar", avatar));
		params.add(new BasicNameValuePair("personaname", personaname));
		params.add(new BasicNameValuePair("gaid", gaid));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject getUserInfo (String steamid64){

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONObject json = jsonParser.getJSONFromUrlGET("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=9634AAA6DEFE22AE3774D9C4C2FE9AB7&steamids=="+steamid64, params);
		return json;
	}

	public JSONObject getUserByGAID(String gaid){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.get_user_by_gaid_tag));
		params.add(new BasicNameValuePair("gaid", gaid));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject getItem(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.get_item_tag));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject setInvitedBy(String userid, String invitedcode){
		
		return null;
	}
//	public JSONObject getCoinsByID(String id)
//	{
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("tag", GlobalVariables.get_coins_tag));
//		params.add(new BasicNameValuePair("userid", id));
//		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
//		return json;
//	}
//	public JSONArray loadHistory(String id)
//	{
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("tag", GlobalVariables.load_history_tag));
//		params.add(new BasicNameValuePair("userid", id));
//		JSONArray json = jsonParser.getArrayFromURL(GlobalVariables.apiURL, params);
//		return json;
//	}
//	public JSONArray loadOffers()
//	{
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("task", GlobalVariables.load_offers_tag));
//		JSONArray json = jsonParser.getArrayFromURL(GlobalVariables.apiURL, params);
//		return json;
//	}


}