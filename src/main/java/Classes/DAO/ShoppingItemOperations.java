package Classes.DAO;

import Classes.Models.ShoppingItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ShoppingItemOperations implements DAO<ShoppingItem> {

    public void EmptyOrders(int Cart_id) throws SQLException{
        String query = "DELETE FROM \"ShoppingItem\" "
                + "WHERE CartID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Cart_id);
        p.executeUpdate();
        p.close();
    }

    public Optional<ShoppingItem> get(int CartID,int otherID) throws SQLException {
        String query = "SELECT * "
                + "FROM \"ShoppingItem\" "
                + "WHERE CartID = ? AND ProductID = ?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,CartID);
        p.setInt(2, otherID);
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
    public boolean add(ShoppingItem item) throws SQLException {
        Optional<ShoppingItem> ResultItem = get(item.getCartID(),item.getProduct().getID());
        if(ResultItem.isPresent()){
            int newQuantity = ResultItem.get().getQuantity()+1;
            ResultItem.get().setQuantity(newQuantity);
            update(ResultItem.get());
        }else{
            InsertQuery(item);
        }
        return true;
    }

    private boolean InsertQuery(ShoppingItem order) throws SQLException {
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
