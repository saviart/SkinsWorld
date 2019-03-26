package net.skinsworld.model;

public class Order {
    private String ID;
    private String Item_ID;
    private String User_ID;
    private String CreatedDate;
    private String Status;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getItem_ID() {
        return Item_ID;
    }

    public void setItem_ID(String item_ID) {
        Item_ID = item_ID;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
