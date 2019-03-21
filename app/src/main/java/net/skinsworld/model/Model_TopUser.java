package net.skinsworld.model;

public class Model_TopUser {

    private int imgavt;
    private String username,membersince,currentpoint;

    public Model_TopUser(int imgavt, String username, String membersince, String currentpoint) {
        this.imgavt = imgavt;
        this.username = username;
        this.membersince = membersince;
        this.currentpoint = currentpoint;
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

    public String getCurrentpoint() {
        return currentpoint;
    }

    public void setCurrentpoint(String currentpoint) {
        this.currentpoint = currentpoint;
    }
}
