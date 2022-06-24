package Classes.DAO;

import Classes.Models.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderCollectionOperations implements DAO<OrderCollection>{

    @Override
    public Optional<OrderCollection> get(int id) throws SQLException {
        String query = "SELECT OrderID,Timestamp "
                + "FROM \"Order\" "
                + "WHERE UserID = ? "
                + "ORDER BY OrderID DESC LIMIT 1 ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,id);
        ResultSet rest = p.executeQuery();
        Optional<OrderCollection> Collection = Optional.empty();
        if(rest.next()) {
            Collection = Optional.of(new OrderCollection());
            Collection.get().setCollectionID(rest.getInt(1));
            System.out.println(Collection.get().getCollectionID());
            Collection.get().setTimestamp(rest.getDate(2));
        }
        p.close();
        return Collection;
    }

    public boolean add(OrderCollection orderCollection)throws  SQLException{
        return false;
    }

    public boolean add(int User_id) throws SQLException {
        String query = "INSERT INTO \"Order\" (UserID, Timestamp) "
                + "VALUES (?,?) ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,User_id);
        p.setDate(2, Date.valueOf(java.time.LocalDate.now()));
        p.executeUpdate();
        p.close();
        return true;
        }

    public boolean AddSingleOrders(int User_id) throws SQLException {
        CartOperation cartOperation = new CartOperation();
        Optional<Cart> cart = cartOperation.get(User_id);
        Optional<OrderCollection> collection = get(User_id);
        OrderOperations orderOperations = new OrderOperations();
        List<ShoppingItem> shoppingItems = cartOperation.getAll(cart.get().getCart_id());
        for(ShoppingItem item: shoppingItems){
            orderOperations.add(collection.get().getCollectionID(),item);
        }
        return true;
    }


    public List<OrderCollection> getAll(int User_id) throws SQLException{
        List<OrderCollection> orderCollectionList = new ArrayList<>();
        String query = "SELECT OrderID,TimeStamp "
                + "FROM \"Order\" "
                + "WHERE UserID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,User_id);
        ResultSet resultSet = p.executeQuery();
        while (resultSet.next()){
            OrderCollection orderCollection = new OrderCollection();
            orderCollection.setUser_ID(User_id);
            orderCollection.setCollectionID(resultSet.getInt(1));
            orderCollection.setTimestamp(resultSet.getDate(2));
            orderCollectionList.add(orderCollection);
        }
        return orderCollectionList;
    }

    /*public List<Order> getAll(int CollectionID) throws SQLException{
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT Quantity,ProductID "
                + "FROM \"Order\" join ItemOrder I on `Order`.OrderID = I.OrderID "
                + "join products p on p.ID = i.ProductID "
                + "WHERE OrderID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,CollectionID);
        ResultSet resultSet = p.executeQuery();
        while (resultSet.next()){
            ProductOperations productOperations = new ProductOperations();
            Optional<Product> product = productOperations.get(resultSet.getInt(2));
            Order order = null;
            if(product.isPresent()){
                int quantity = resultSet.getInt(1);
                order = new Order(quantity,product.get(),CollectionID);
            }
            orderList.add(order);
        }
        return orderList;
    }*/


    @Override
    public void update(OrderCollection orderCollection) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
