package Classes.DAO;

import Classes.Models.Cart;
import Classes.Models.ShoppingItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ShoppingItemOperations implements DAO<ShoppingItem> {

    CartOperation cartOperation;
    public ShoppingItemOperations(CartOperation cartOperation){
        this.cartOperation = cartOperation;
    }
    @Override
    public Optional<ShoppingItem> get(Integer productId) throws SQLException {
        String query = "SELECT * "
                + "FROM \"ShoppingItem\" "
                + "WHERE CartID = ? AND ProductID = ?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        Integer cartId = cartOperation.getNow().get().getCart_id();
        p.setInt(1,cartId);
        p.setInt(2, productId);
        ResultSet rest = p.executeQuery();
        Optional<ShoppingItem> shoppingItem = Optional.empty();
        if (rest.next()) {
            ProductOperations productOperations = new ProductOperations();
            shoppingItem = Optional.of(new ShoppingItem());
            shoppingItem.get().setQuantity(rest.getInt("Quantity"));
            shoppingItem.get().setCartID(cartId);
            shoppingItem.get().setProduct(productOperations.get(productId).get());
        }
        p.close();
        return shoppingItem;
    }


    @Override
    public boolean add(ShoppingItem item) throws SQLException {
        Optional<ShoppingItem> ResultItem = get(item.getProduct().getID());
        if(!ResultItem.isPresent())
            InsertQuery(item);
        Integer newQuantity = ResultItem.get().getQuantity()+1;
        ResultItem.get().setQuantity(newQuantity);
        update(ResultItem.get());
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


    public void delete(Integer ProductID) throws SQLException {
        String query = "DELETE FROM \"ShoppingItem\" "
                + "WHERE CartID=? AND ProductID=? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        if(!cartOperation.getNow().isPresent())
            throw new SQLException("Product is not present");
        Integer cartId = cartOperation.getNow().get().getCart_id();
        p.setInt(1,cartId);
        p.setInt(2,ProductID);
        p.executeUpdate();
        p.close();
    }
}
