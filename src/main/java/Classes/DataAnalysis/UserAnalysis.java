package Classes.DataAnalysis;

import Classes.Cart.CartOperation;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.Order.OrderOperations;
import Classes.Order.Order;
import Classes.OrderCollection.OrderCollection;
import Classes.Product.ProductModel;
import Classes.Product.ProductOperations;

import java.sql.SQLException;
import java.util.*;

public class UserAnalysis {
    Map<String,Integer> PurchasePerCategory = new HashMap<>();
    Integer User_id;
    List<OrderCollection> Orders;

    public UserAnalysis(Integer User_id, List<OrderCollection> orders){
        this.User_id = User_id;
        this.Orders = orders;
    }

    public Map<String,Integer> getPurchasePerCategory() throws SQLException {
        for(OrderCollection collection : Orders){
            OrderOperations orderOperations = new OrderOperations(collection.getCollectionID());
            List<Order> Items = orderOperations.getAll();
            for(Order order : Items){
                String orderCategory = order.getItem().getCategory();
                Integer currentPurchases = PurchasePerCategory.get(orderCategory);
                if (currentPurchases == null) {
                    PurchasePerCategory.put(orderCategory, 1);
                } else {
                    PurchasePerCategory.put(orderCategory, currentPurchases + 1);
                }
            }
    }
        return PurchasePerCategory;
    }

    public Map.Entry<String,Integer> getMaxMap(Map<String,Integer> map){
        Map.Entry<String,Integer> maxEntry = null;
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(maxEntry == null || entry.getValue() > maxEntry.getValue())
                maxEntry = entry;
        }
        return maxEntry;
    }

    public List<ProductModel> getSuggestions(String category) throws SQLException {
        ProductOperations productOperations = new ProductOperations();
        List<ProductModel> productModelList = productOperations.getAllByCategory(category);
        OrderCollectionOperations orderCollectionOperations = new OrderCollectionOperations(new CartOperation(User_id));
        List<OrderCollection> orders = orderCollectionOperations.getAll(User_id);
        for(OrderCollection order : orders){
            OrderOperations orderOperations = new OrderOperations(order.getCollectionID());
            List<Order> orderList = orderOperations.getAll();
            for(Order item : orderList){
                    productModelList.removeIf(element -> element.getID() == item.getItem().getID());
            }
        }
        return productModelList;
    }
}

