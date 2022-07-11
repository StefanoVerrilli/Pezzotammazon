package Classes.Order;

import Classes.Product.ProductModel;
import Classes.ShoppingItem.ShoppingItemModel;

public class OrderModel {

    private Integer ContainerID;

    private Integer quantity;

    private ProductModel item;


    public OrderModel(int OrderID, ShoppingItemModel item){
        this.quantity = item.getQuantity();
        this.ContainerID = OrderID;
        this.item = item.getProduct();
    }

    public OrderModel(int quantity, ProductModel product, int OrderID){
        this.quantity = quantity;
        this.item = product;
        this.ContainerID = OrderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Integer getContainerID() {
        return ContainerID;
    }

    public ProductModel getItem(){return item;}

}
