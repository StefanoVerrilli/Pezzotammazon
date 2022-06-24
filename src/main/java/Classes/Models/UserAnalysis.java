package Classes.Models;

import Classes.DAO.OrderOperations;
import Classes.TFIDF.TFIDF;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}

