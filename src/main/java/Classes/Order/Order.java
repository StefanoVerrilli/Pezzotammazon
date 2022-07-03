package Classes.Order;

import Classes.Product.ProductModel;
import Classes.ShoppingItem.ShoppingItemModel;

import java.util.Objects;

public class Order {

    private Integer ContainerID;

    private Integer quantity;

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

    public Integer getContainerID() {
        return ContainerID;
    }

    public ProductModel getItem(){return item;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;}
        if (obj == null){
            return false;}
        Order otherProduct = (Order) obj;
        return Objects.equals(item.getID(), otherProduct.item.getID());
    }

    @Override
    public int hashCode(){
    return item.getID();
    }

}
