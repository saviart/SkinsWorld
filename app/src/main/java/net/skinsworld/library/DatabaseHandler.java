package net.skinsworld.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.skinsworld.model.User;


public class DatabaseHandler extends SQLiteOpenHelper {




	public DatabaseHandler(Context context) {
		super(context, GlobalVariables.DATABASE_NAME, null, GlobalVariables.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + GlobalVariables.TABLE_LOGIN + "("
				+ GlobalVariables.KEY_ID + " TEXT PRIMARY KEY,"			//0
				+ GlobalVariables.KEY_STEAM_ID_64	+ " TEXT,"			//1
				+ GlobalVariables.KEY_TRADE_URL + " TEXT,"				//2
				+ GlobalVariables.KEY_COINS + " TEXT,"					//3
				+ GlobalVariables.KEY_CREATED_DATE + " TEXT,"			//4
				+ GlobalVariables.KEY_ACTIVE + " TEXT,"					//5
				+ GlobalVariables.KEY_GAID + " TEXT,"					//6
				+ GlobalVariables.KEY_INVITED_BY + " TEXT,"				//7
				+ GlobalVariables.KEY_AVATAR + " TEXT,"					//8
				+ GlobalVariables.KEY_PERSONA_NAME + " TEXT" + ")";		//9
		db.execSQL(CREATE_LOGIN_TABLE);
	}

	// Cập nhật database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Xóa table cũ
		db.execSQL("DROP TABLE IF EXISTS " + GlobalVariables.TABLE_LOGIN);
		// Khởi tạo lại database
		onCreate(db);
	}

	// Lưu trữ thông tin chi tiết ngư�?i dùng vào database

	public void addUser(User user) {
		resetTables();
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(GlobalVariables.KEY_ID, user.getUserID());
		values.put(GlobalVariables.KEY_STEAM_ID_64, user.getSteamID64());
		values.put(GlobalVariables.KEY_TRADE_URL, user.getTradeURL());
		values.put(GlobalVariables.KEY_COINS, user.getCoins());
		values.put(GlobalVariables.KEY_CREATED_DATE, user.getCreatedDate());
		values.put(GlobalVariables.KEY_ACTIVE, user.getActive());
		values.put(GlobalVariables.KEY_GAID, user.getGAID());
		values.put(GlobalVariables.KEY_INVITED_BY, user.getInvitedBy());
		values.put(GlobalVariables.KEY_AVATAR, user.getAvatar());
		values.put(GlobalVariables.KEY_PERSONA_NAME, user.getPersonaName());
		db.insert(GlobalVariables.TABLE_LOGIN, null, values);
		db.close();
	}

	public User getUser() {
		User user = new User();
		String selectQuery = "SELECT * FROM " + GlobalVariables.TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.setUserID(cursor.getString(0));
			user.setSteamID64(cursor.getString(1));
			user.setTradeURL(cursor.getString(2));
			user.setCoins(cursor.getString(3));
			user.setCreatedDate(cursor.getString(4));
			user.setActive(cursor.getString(5));
			user.setGAID(cursor.getString(6));
			user.setInvitedBy(cursor.getString(7));
			user.setAvatar(cursor.getString(8));
			user.setPersonaName(cursor.getString(9));
		}
		db.close();
		return user;
	}

	// Xem trạng thái đăng nhập, trả v�? số row trong table
	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + GlobalVariables.TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();
		return rowCount;
	}

	// Thực hiện xóa tất cả row trong table
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(GlobalVariables.TABLE_LOGIN, null, null);
		db.close();
	}

}