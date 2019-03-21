package net.skinsworld.model;

public class Model_TransactionHistory {
    private int trans_skinsimage;
    private String trans_username,trans_skinsname,trans_time;

    public Model_TransactionHistory(int trans_skinsimage, String trans_username, String trans_skinsname, String trans_time) {
        this.trans_skinsimage = trans_skinsimage;
        this.trans_username = trans_username;
        this.trans_skinsname = trans_skinsname;
        this.trans_time = trans_time;
    }

    public int getTrans_skinsimage() {
        return trans_skinsimage;
    }

    public void setTrans_skinsimage(int trans_skinsimage) {
        this.trans_skinsimage = trans_skinsimage;
    }

    public String getTrans_username() {
        return trans_username;
    }

    public void setTrans_username(String trans_username) {
        this.trans_username = trans_username;
    }

    public String getTrans_skinsname() {
        return trans_skinsname;
    }

    public void setTrans_skinsname(String trans_skinsname) {
        this.trans_skinsname = trans_skinsname;
    }

    public String getTrans_time() {
        return trans_time;
    }

    public void setTrans_time(String trans_time) {
        this.trans_time = trans_time;
    }
}
