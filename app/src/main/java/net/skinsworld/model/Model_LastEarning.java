package net.skinsworld.model;

public class Model_LastEarning {

    private String name_offer,time_complete,numb_earn_point;

    public Model_LastEarning(String name_offer, String time_complete, String numb_earn_point) {
        this.name_offer = name_offer;
        this.time_complete = time_complete;
        this.numb_earn_point = numb_earn_point;
    }


    public String getName_offer() {
        return name_offer;
    }

    public void setName_offer(String name_offer) {
        this.name_offer = name_offer;
    }

    public String getTime_complete() {
        return time_complete;
    }

    public void setTime_complete(String time_complete) {
        this.time_complete = time_complete;
    }

    public String getNumb_earn_point() {
        return numb_earn_point;
    }

    public void setNumb_earn_point(String numb_earn_point) {
        this.numb_earn_point = numb_earn_point;
    }
}

