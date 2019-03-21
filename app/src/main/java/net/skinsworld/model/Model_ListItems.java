package net.skinsworld.model;

public class Model_ListItems {

    private int imgitem;
    private String nameitem,desitem,numbcoin,txtgame;

    public Model_ListItems(int imgitem, String nameitem, String desitem, String numbcoin, String txtgame) {
        this.imgitem = imgitem;
        this.nameitem = nameitem;
        this.desitem = desitem;
        this.numbcoin = numbcoin;
        this.txtgame = txtgame;
    }

    public int getImgitem() {
        return imgitem;
    }

    public void setImgitem(int imgitem) {
        this.imgitem = imgitem;
    }




    public String getNameitem() {
        return nameitem;
    }

    public void setNameitem(String nameitem) {
        this.nameitem = nameitem;
    }

    public String getDesitem() {
        return desitem;
    }

    public void setDesitem(String desitem) {
        this.desitem = desitem;
    }

    public String getNumbcoin() {
        return numbcoin;
    }

    public void setNumbcoin(String numbcoin) {
        this.numbcoin = numbcoin;
    }

    public String getTxtgame() {
        return txtgame;
    }

    public void setTxtgame(String txtgame) {
        this.txtgame = txtgame;
    }
}
