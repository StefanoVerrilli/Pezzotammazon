package Classes;


import java.math.BigDecimal;

public class Order {

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

    public double getSubTotal() {
        float subtotal = this.product.getCost()*quantity;
        return (Math.round(subtotal*100.0)/100.0);
    }

    public void setProduct(Product product){this.product = product;}
    public Product getProduct(){return this.product;}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
