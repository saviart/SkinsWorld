package net.skinsworld.library;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
	public JSONObject buyItem(String itemID, String userID)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.buy_item_tag));
		params.add(new BasicNameValuePair("itemid", itemID));
		params.add(new BasicNameValuePair("userid", userID));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}

	public JSONObject loadOrder(String UserID){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.load_order_tag));
		params.add(new BasicNameValuePair("userid", UserID));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject loadMyHistory(String UserID){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.load_my_history_tag));
		params.add(new BasicNameValuePair("userid", UserID));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject loadRecent(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.load_recent_order_tag));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject setInvitedBy(String userid, String invitedcode){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.set_invited_by_tag));
		params.add(new BasicNameValuePair("userid", userid));

		long steamID32 = Long.parseLong(invitedcode.substring(2));
		long steamID64 = steamID32 + Long.parseLong("76561197960265728");
		String invitedUser = steamID64+"";
		params.add(new BasicNameValuePair("invitedUser", invitedUser));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject getDailyCoins(String userid){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.get_daily_coins_tag));
		params.add(new BasicNameValuePair("userid", userid));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
	}
	public JSONObject setTradeURL(String userid, String tradeurl)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", GlobalVariables.set_trade_url_tag));
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("tradeurl", tradeurl));
		JSONObject json = jsonParser.getJSONFromUrl(GlobalVariables.apiURL, params);
		return json;
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