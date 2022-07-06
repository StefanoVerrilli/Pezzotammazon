package Classes.ShoppingItem;


import Classes.Product.ProductModel;

public class ShoppingItemModel {

    private ProductModel product;

    private Integer CartID;
    private Integer quantity = 1;

    public ShoppingItemModel(ProductModel product, Integer CartID){
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

    public void setProduct(ProductModel product){this.product = product;}
    public ProductModel getProduct(){return this.product;}

}
