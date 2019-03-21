package net.skinsworld.model;

public class User {

	private String id;
	private String steamid64;
	private String tradeURL;
	private String coins;
	private String created_date;
	private String active;
	private String gaid;
	private String invited_by;
	private String avatar;
	private String personaName;
	public User() {
		super();
	}
	public User(String steamid64, String created_date, String coins, String gaid) {
		this.steamid64 = steamid64;
		this.created_date = created_date;
		this.coins = coins;
		this.gaid = gaid;
	}
	public User(String steamid64, String avatar, String personaName)
	{
		this.steamid64 = steamid64;
		this.avatar = avatar;
		this.personaName = personaName;
	}

	public String getPersonaName() {
		return personaName;
	}

	public void setPersonaName(String personaName) {
		this.personaName = personaName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvited_by() {
		return invited_by;
	}

	public void setInvited_by(String invited_by) {
		this.invited_by = invited_by;
	}

	public String getSteamid64() {
		return steamid64;
	}

	public void setSteamid64(String steamid64) {
		this.steamid64 = steamid64;
	}

	public String getTradeURL() {
		return tradeURL;
	}

	public void setTradeURL(String tradeURL) {
		this.tradeURL = tradeURL;
	}

	public String getCoins() {
		return coins;
	}

	public void setCoins(String coins) {
		this.coins = coins;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getGaid() {
		return gaid;
	}

	public void setGaid(String gaid) {
		this.gaid = gaid;
	}
}
