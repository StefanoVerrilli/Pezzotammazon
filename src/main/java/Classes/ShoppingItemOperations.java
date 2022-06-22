package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingItemOperations implements DAO<ShoppingItem>{

    public void EmptyOrders(int Cart_id) throws SQLException{
        String query = "DELETE FROM \"ShoppingItem\" "
                + "WHERE CartID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Cart_id);
        p.executeUpdate();
        p.close();
    }

    public Optional<ShoppingItem> get(ShoppingItem item) throws SQLException {
        System.out.println(item.getProduct().getID());
        System.out.println(item.getCartID());
        String query = "SELECT * "
                + "FROM \"ShoppingItem\" "
                + "WHERE CartID = ? AND ProductID = ?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,item.getCartID());
        p.setInt(2,item.getProduct().getID());
        ResultSet rest = p.executeQuery();
        Optional<ShoppingItem> shoppingItem = Optional.empty();
        if(rest.next()){
            shoppingItem = Optional.of(new ShoppingItem());
        shoppingItem.get().setQuantity(rest.getInt("Quantity"));
        shoppingItem.get().setCartID(rest.getInt("CartID"));
        ProductOperations productOperations = new ProductOperations();
        shoppingItem.get().setProduct(productOperations.get(rest.getInt("ProductID")).get());}
        p.close();
        return shoppingItem;
    }


    @Override
    public Optional<ShoppingItem> get(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean add(ShoppingItem order) throws SQLException {
        String query = "INSERT INTO \"ShoppingItem\" (quantity,CartID,ProductID) "
                + "VALUES (?,?,?)";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,order.getQuantity());
        p.setInt(2,order.getCartID());
        p.setInt(3,order.getProduct().getID());
        p.executeUpdate();
        p.close();
        return true;
    }

    private Integer CheckOrderExist(int cart_id, int item_id)throws SQLException{
        String query ="SELECT  ProductID "
                + "FROM \"ShoppingItem\" "
                + "WHERE CartID =? AND ProductID=?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,cart_id);
        p.setInt(2,item_id);
        ResultSet rest = p.executeQuery();
        Integer orderId = null;
        if(rest.next()){orderId = rest.getInt(1);}
        p.close();
        return orderId;
    }

    @Override
    public void update(ShoppingItem order) throws SQLException {
        String query = "UPDATE \"ShoppingItem\" "
                + "SET Quantity=? "
                + "WHERE CartID=? AND ProductID=?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,order.getQuantity());
        p.setInt(2,order.getCartID());
        p.setInt(3,order.getProduct().getID());
        p.executeUpdate();
        p.close();
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    public void delete(int CartID,int ProductID) throws SQLException {
        String query = "DELETE FROM \"ShoppingItem\" "
                + "WHERE CartID=? AND ProductID=? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,CartID);
        p.setInt(2,ProductID);
        p.executeUpdate();
        p.close();
    }
}
