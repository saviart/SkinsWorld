package net.skinsworld.library;




import net.skinsworld.model.Item;
import net.skinsworld.model.Order;
import net.skinsworld.model.User;

import java.util.ArrayList;


public class GlobalVariables {

	public static final String apiURL = "http://skinsworld.net/api/";
	public static final String get_user_by_gaid_tag = "get_user_by_gaid";
	public static final String login_with_steam_tag = "login";
	public static final String get_item_tag = "get_item";
	public static final String buy_item_tag = "buy_item";
	public static final String load_order_tag = "load_order";
	public static final String set_invited_by_tag = "set_invited_by";
	public static final String KEY_ID = "UserID";
	public static final String KEY_STEAM_ID_64 = "SteamID64";
	public static final String KEY_TRADE_URL = "TradeURL";
	public static final String KEY_COINS = "Coins";
	public static final String KEY_CREATED_DATE = "CreatedDate";
	public static final String KEY_ACTIVE = "Active";
	public static final String KEY_GAID = "GAID";
	public static final String KEY_INVITED_BY = "InvitedBy";
	public static final String KEY_AVATAR = "Avatar";
	public static final String KEY_PERSONA_NAME = "PersonaName";
	public static final int DATABASE_VERSION = 1;
	public static User user = new User();
	public static final String DATABASE_NAME = "AppDB";
	public static final String TABLE_LOGIN = "USER";
	public static final int appVersion = 1;
	public static String gaid="";
	public static ArrayList<Item> listItem;
	public static ArrayList<Order> listOrder;

	
}