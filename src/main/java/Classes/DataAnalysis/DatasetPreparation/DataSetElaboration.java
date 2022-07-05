package Classes.DataAnalysis.DatasetPreparation;

import Classes.Clustering.Record;
import Classes.Order.Order;
import Classes.Order.OrderOperations;
import Classes.OrderCollection.OrderCollection;
import Classes.Product.ProductCategoriesOperations;
import Classes.Product.ProductCategoryModel;
import Classes.Product.ProductModel;
import Classes.Product.ProductOperations;
import Classes.User.UserModel;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DataSetElaboration {

    public Record getData(UserModel user, List<OrderCollection> orderCollectionList) throws SQLException{

        ProductCategoriesOperations productCategoriesOperations = new ProductCategoriesOperations();
        List<ProductCategoryModel> categoryModels = productCategoriesOperations.getAll();
        Map<String,Integer> data = categoryModels.stream()
                                           .collect(Collectors.toMap(ProductCategoryModel::getCategoryDescription,
                                           ProductCategoryModel -> 0));
        for(OrderCollection collection : orderCollectionList){
            OrderOperations orderOperations = new OrderOperations(collection.getCollectionID());
            List<Order> items = orderOperations.getAll();
            for(Order order : items){
                data.compute(order.getItem().getCategory().getCategoryDescription(),(key,value) -> {
                    if(value == null)
                        value = order.getQuantity();
                    value += order.getQuantity();
                    return value;
                });
            }
        }
        return new Record(user,data);
    }

    public String MaxPurchaseCategory(Record userRecord){
        Map<String,Integer> features = userRecord.getFeatures();
        Integer Max = 0;
        String Category ="";
        for(String category : features.keySet()){
            Integer numPurchase = features.get(category);
            if(numPurchase > Max){
                Max = numPurchase;
                Category = category;
            }
        }
    Map.Entry<String,Integer> map = new AbstractMap.SimpleEntry<>(Category,Max);
    System.out.println("La categoria con maggior numero di acquisti è: " + map.getKey() + " con ben: " +
    map.getValue() + " acqusti.");
    return Category;
    }

    public List<ProductModel> getSuggestions(String Category,List<OrderCollection> orderCollectionList) throws SQLException {
        ProductOperations productOperations = new ProductOperations(new ProductCategoriesOperations());
        List<ProductModel> products = productOperations.getAllByCategory(Category);
        Set<Order> orderSet = new HashSet<>();
        for(OrderCollection collection : orderCollectionList){
            OrderOperations orderOperations = new OrderOperations(collection.getCollectionID());
            List<Order> orders = orderOperations.getAllByCategory(Category);
            for(Order order : orders)
                orderSet.add(order);
        }
        products.removeIf(e -> orderSet.stream()
        .anyMatch(order -> order.getItem().getID() == e.getID()));
        return products;
    }

    public List<ProductModel> Suggestor(List<OrderCollection> orderCollectionList,UserModel User) throws SQLException{
        Record record = getData(User,orderCollectionList);
        String preferredCategory = MaxPurchaseCategory(record);
        return getSuggestions(preferredCategory,orderCollectionList);
    }

}
