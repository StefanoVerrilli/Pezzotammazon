package Classes;

import Classes.Observer.Subscriber;

public class Order implements Subscriber {

    private Product product;

    private int user_id;

    private int ID;
    private int quantity = 1;

    public Order(){
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getSubTotal() {
        return this.product.getCost()*quantity;
    }

    public void setProduct(Product product){this.product = product;}
    public Product getProduct(){return this.product;}

    @Override
    public void update(Product product) {
        this.product = product;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
