package net.skinsworld.model;

public class User {

	private String UserID;
	private String SteamID64;
	private String TradeURL;
	private String Coins;
	private String CreatedDate;
	private String Active;
	private String GAID;
	private String InvitedBy;
	private String Avatar;
	private String PersonaName;
	public User() {
		super();
	}
	public User(String steamid64, String created_date, String coins, String gaid) {
		this.SteamID64 = steamid64;
		this.CreatedDate = created_date;
		this.Coins = coins;
		this.GAID = gaid;
	}
	public User(String steamid64, String avatar, String personaName)
	{
		this.SteamID64 = steamid64;
		this.Avatar = avatar;
		this.PersonaName = personaName;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getSteamID64() {
		return SteamID64;
	}

	public void setSteamID64(String steamID64) {
		SteamID64 = steamID64;
	}

	public String getTradeURL() {
		return TradeURL;
	}

	public void setTradeURL(String tradeURL) {
		TradeURL = tradeURL;
	}

	public String getCoins() {
		return Coins;
	}

	public void setCoins(String coins) {
		Coins = coins;
	}

	public String getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}

	public String getActive() {
		return Active;
	}

	public void setActive(String active) {
		Active = active;
	}

	public String getGAID() {
		return GAID;
	}

	public void setGAID(String GAID) {
		this.GAID = GAID;
	}

	public String getInvitedBy() {
		return InvitedBy;
	}

	public void setInvitedBy(String invitedBy) {
		InvitedBy = invitedBy;
	}

	public String getAvatar() {
		return Avatar;
	}

	public void setAvatar(String avatar) {
		Avatar = avatar;
	}

	public String getPersonaName() {
		return PersonaName;
	}

	public void setPersonaName(String personaName) {
		PersonaName = personaName;
	}
}
