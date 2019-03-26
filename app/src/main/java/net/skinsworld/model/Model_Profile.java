package net.skinsworld.model;

public class Model_Profile {

    private Order order;
    private Item item;

    public Model_Profile(){}

    public Model_Profile(Order order, Item item){
        this.order = order;
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
