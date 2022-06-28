package Classes.Order;

import Classes.Product.ProductModel;
import Classes.ShoppingItem.ShoppingItemModel;

public class Order {

    private int ContainerID;

    private int quantity;

    private ProductModel item;


    public Order(int OrderID, ShoppingItemModel item){
        this.quantity = item.getQuantity();
        this.ContainerID = OrderID;
        this.item = item.getProduct();
    }

    public Order(int quantity, ProductModel product, int OrderID){
        this.quantity = quantity;
        this.item = product;
        this.ContainerID = OrderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getContainerID() {
        return ContainerID;
    }

    public ProductModel getItem(){return item;}
}
