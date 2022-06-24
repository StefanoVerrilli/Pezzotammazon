package Classes.DAO;

import Classes.Models.Order;
import Classes.Models.Product;
import Classes.Models.ShoppingItem;
import Classes.Strategy.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Order> getAll(int CollectionID) throws SQLException{
        List<Order> result = new ArrayList<>();
        String query = "SELECT Quantity,Name,Image,Description,ID,Amount,Cost,Category "
                + "FROM ItemOrder join products p on p.ID = ItemOrder.ProductID "
                + "WHERE OrderID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,CollectionID);
        ResultSet rest = p.executeQuery();
        while (rest.next()){
            Product newProduct = new Product();
            newProduct.setName(rest.getString(2));
            newProduct.setImage(rest.getString(3));
            newProduct.setDesc(rest.getString(4));
            newProduct.setID(rest.getInt(5));
            newProduct.setAmount(rest.getInt(6));
            newProduct.setCost(rest.getFloat(7));
            newProduct.setCategory(rest.getString(8));
            Order order = new Order(rest.getInt(1),newProduct,CollectionID);
            result.add(order);
        }
        return result;
    }

    @Override
    public void update(Order order) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
