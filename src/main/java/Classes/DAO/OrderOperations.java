package Classes.DAO;

import Classes.Models.Order;
import Classes.Models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderOperations implements DAO<Order>{
    private Integer collectionID;
    public OrderOperations(Integer collectionID){
        this.collectionID = collectionID;
    }
    @Override
    public Optional<Order> get(Integer id) throws SQLException {
        return Optional.empty();
    }

    public boolean add(Order order) throws SQLException{
        String query ="INSERT INTO ItemOrder(Quantity, OrderID, ProductID) "
                + "VALUES(?,?,?) ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,order.getQuantity());
        p.setInt(2,collectionID);
        p.setInt(3,order.getItem().getID());
        p.executeUpdate();
        p.close();
        return true;
    }

    public List<Order> getAll() throws SQLException{
        List<Order> result = new ArrayList<>();
        String query = "SELECT Quantity,Name,Image,Description,ID,Amount,Cost,Category "
                + "FROM ItemOrder join products p on p.ID = ItemOrder.ProductID "
                + "WHERE OrderID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,this.collectionID);
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
            Order order = new Order(rest.getInt(1),newProduct,collectionID);
            result.add(order);
        }
        return result;
    }

    @Override
    public void update(Order order) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
