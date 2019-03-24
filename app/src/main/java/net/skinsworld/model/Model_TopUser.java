package net.skinsworld.model;

public class Model_TopUser {

    private int imgavt;
    private String username,membersince,currentcoins;

    public Model_TopUser(int imgavt, String username, String membersince, String currentcoins) {
        this.imgavt = imgavt;
        this.username = username;
        this.membersince = membersince;
        this.currentcoins = currentcoins;
    }

    public int getImgavt() {
        return imgavt;
    }

    public void setImgavt(int imgavt) {
        this.imgavt = imgavt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMembersince() {
        return membersince;
    }

    public void setMembersince(String membersince) {
        this.membersince = membersince;
    }

    public String getCurrentcoins() {
        return currentcoins;
    }

    public void setCurrentcoins(String currentcoins) {
        this.currentcoins = currentcoins;
    }
}
