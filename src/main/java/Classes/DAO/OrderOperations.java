package Classes.DAO;

import Classes.Models.Order;
import Classes.Models.ShoppingItem;
import Classes.Strategy.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderOperations implements DAO<Order>{
    @Override
    public Optional<Order> get(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean add(Order order) throws SQLException {
        return false;
    }
    public boolean add(int CollectionID,ShoppingItem item) throws SQLException{
        String query ="INSERT INTO ItemOrder(Quantity, OrderID, ProductID) "
                + "VALUES(?,?,?) ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,item.getQuantity());
        p.setInt(2,CollectionID);
        p.setInt(3,item.getProduct().getID());
        p.executeUpdate();
        p.close();
        return true;
    }

    @Override
    public void update(Order order) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
