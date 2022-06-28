package Classes.ShoppingItem;


import Classes.Models.Product;

public class ShoppingItemModel {

    private Product product;

    private Integer CartID;
    private int quantity = 1;

    public ShoppingItemModel(Product product, Integer CartID){
        this.product = product;
        this.CartID = CartID;
    }

    public ShoppingItemModel(){}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartID() {
        return this.CartID;
    }

    public void setCartID(int ID) {
        this.CartID = ID;
    }

    public double getSubTotal() {
        float subtotal = this.product.getCost()*quantity;
        return (Math.round(subtotal*100.0)/100.0);
    }

    public void setProduct(Product product){this.product = product;}
    public Product getProduct(){return this.product;}

}
