package Classes.Order;

import Classes.Product.ProductModel;
import Classes.ShoppingItem.ShoppingItemModel;

/**
 * Modello che rappresenta un oggetto in un ordine.
 */

public class OrderModel {

    /**
     * Identificativo ordine.
     */
    private Integer ContainerID;

    /**
     * Quantità del prodotto ordinata.
     */
    private Integer quantity;

    /**
     * Prodotto ordinato.
     */
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

    /**
     * Ottiene la quantità.
     * @return Quantità del prodotto ordinata.
     */
    public int getQuantity() {
        return quantity;
    }

    public Integer getContainerID() {
        return ContainerID;
    }

    /**
     * Ottiene il prodotto ordinato.
     * @return Prodotto ordinato.
     */
    public ProductModel getItem(){return item;}

}
