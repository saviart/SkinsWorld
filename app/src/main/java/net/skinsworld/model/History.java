package net.skinsworld.model;

public class History {
    private String ID;
    private String UserID;
    private String Time;
    private String Network;
    private String Coins;
    private String OfferName;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getNetwork() {
        return Network;
    }

    public void setNetwork(String network) {
        Network = network;
    }

    public String getCoins() {
        return Coins;
    }

    public void setCoins(String coins) {
        Coins = coins;
    }

    public String getOfferName() {
        return OfferName;
    }

    public void setOfferName(String offerName) {
        OfferName = offerName;
    }
}
