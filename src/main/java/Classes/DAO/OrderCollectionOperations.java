package Classes.DAO;

import Classes.Models.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderCollectionOperations implements DAO<OrderCollection>{

    @Override
    public Optional<OrderCollection> get(int id) throws SQLException {
        String query = "SELECT OrderID,Timestamp "
                + "FROM \"Order\" "
                + "WHERE UserID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,id);
        ResultSet rest = p.executeQuery();
        Optional<OrderCollection> Collection = Optional.empty();
        if(rest.next()){
            Collection = Optional.of(new OrderCollection());
            Collection.get().setCollectionID(rest.getInt(1));
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
        System.out.println("so qua");
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        System.out.println("ririprovo");
        p.setInt(1,User_id);
        System.out.println("riproviamo");
        p.setDate(2, Date.valueOf(java.time.LocalDate.now()));
        p.executeUpdate();
        p.close();
        return true;
        }

    public boolean AddSingleOrders(int User_id) throws SQLException {
        CartOperation cartOperation = new CartOperation();
        System.out.println("santo dio");
        Optional<Cart> cart = cartOperation.get(User_id);
        Optional<OrderCollection> collection = get(User_id);
        OrderOperations orderOperations = new OrderOperations();
        List<ShoppingItem> shoppingItems = cartOperation.getAll(cart.get().getCart_id());
        for(ShoppingItem item: shoppingItems){
            orderOperations.add(collection.get().getCollectionID(),item);
        }
        return true;
    }

    @Override
    public void update(OrderCollection orderCollection) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
