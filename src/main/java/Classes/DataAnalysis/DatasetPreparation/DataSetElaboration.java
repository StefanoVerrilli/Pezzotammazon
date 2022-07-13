package Classes.DataAnalysis.DatasetPreparation;

import Classes.Clustering.Record;
import Classes.Order.IOrderOperations;
import Classes.Order.OrderModel;
import Classes.Order.OrderOperations;
import Classes.OrderCollection.OrderCollectionModel;
import Classes.Product.IProductOperations;
import Classes.Product.ProductCategory.IProductCategoryOperations;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductCategory.ProductCategoryModel;
import Classes.Product.ProductModel;
import Classes.Product.ProductOperations;
import Classes.Suggestion.SuggestionModel;
import Classes.Suggestion.SuggestionOperation;
import Classes.User.UserModel;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Contiene metodi per la gestione dei dati per il suggerimento dei prodotti.
 */

public class DataSetElaboration {

    /**
     * Ottiene informazioni sull'utente e la lista degli ordini effettuati.
     * @see Record
     * @param user Utente di cui si vogliono ottenere i dati.
     * @see UserModel
     * @param orderCollectionList Lista contenente gli ordini dell'utente.
     * @return Record contenente i dati.
     * @throws SQLException
     */

    public Record getData(UserModel user, List<OrderCollectionModel> orderCollectionList) throws SQLException{

        IProductCategoryOperations<ProductCategoryModel> productCategoriesOperations =
        new ProductCategoriesOperations();

        List<ProductCategoryModel> categoryModels = productCategoriesOperations.getAll();
        Map<String,Integer> data = categoryModels.stream()
                                           .collect(Collectors.toMap(ProductCategoryModel::getCategoryDescription,
                                           ProductCategoryModel -> 0));
        for(OrderCollectionModel collection : orderCollectionList){
            IOrderOperations<OrderModel> orderOperations = new OrderOperations();
            List<OrderModel> items = orderOperations.getAll(collection.getCollectionID());
            for(OrderModel order : items){
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

    /**
     * Permette di ottenere il nome della categoria con maggiori acquisti per l'utente.
     * @param userRecord Record contentente l'utente e i dati dell'utente da cui calcolare la categoria con maggiori acquisti.
     * @return Nome della categoria con maggiori acquisti.
     * @see Record
     */
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
    return Category;
    }

    /**
     * Ottiene i prodotti suggeriti per l'utente sulla base della categoria con maggiori acquisti.
     * @param Category Categoria da cui ottenere i suggerimenti, solitamente si passa quella con il numero di acquisti maggiori.
     * @param orderCollectionList Lista degli ordini effettuati dall'utente.
     * @return Lista dei prodotti suggeriti.
     * @throws SQLException Errore nell'esecuzione della query sul database.
     */
    public List<ProductModel> getSuggestions(String Category,List<OrderCollectionModel> orderCollectionList) throws SQLException {

        IProductOperations<ProductModel> productOperations =
        new ProductOperations(new ProductCategoriesOperations());

        List<ProductModel> products = productOperations.getAllByCategory(Category);
        Set<OrderModel> orderSet = new HashSet<>();


        for(OrderCollectionModel collection : orderCollectionList){
            IOrderOperations<OrderModel> orderOperations = new OrderOperations();
            List<OrderModel> orders = orderOperations.getAllByCategory(Category,collection.getCollectionID());
            orderSet.addAll(orders);
        }

        /**
         * Non suggerire se il prodotto è già stato acquistato.
         */
        products.removeIf(e -> orderSet.stream()
        .anyMatch(order -> order.getItem().getID() == e.getID()));

        List<SuggestionModel> AlreadySuggested = new SuggestionOperation().getAll(orderCollectionList.get(0).getUser_ID());

        /**
         * Non mostrare tra i suggerimenti se il prodotto è già suggerito.
         */
        products.removeIf(e-> AlreadySuggested.stream()
        .anyMatch(suggestion -> suggestion.getProductID() == e.getID()));

        return products;
    }

}
