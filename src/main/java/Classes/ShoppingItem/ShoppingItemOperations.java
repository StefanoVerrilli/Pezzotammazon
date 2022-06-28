package Classes.ShoppingItem;

import Classes.Cart.CartModel;
import Classes.Cart.CartOperation;
import Classes.Cart.ICartOperation;
import Classes.DAO.*;
import Classes.Product.ProductOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ShoppingItemOperations implements IAddDAO<ShoppingItemModel>, IDeleteDAO,
IGetDAO<ShoppingItemModel>, IUpdateDAO<ShoppingItemModel>{

    ICartOperation cartOperation;
    public ShoppingItemOperations(ICartOperation cartOperation){
        this.cartOperation = cartOperation;
    }
    @Override
    public Optional<ShoppingItemModel> get(Integer productId) throws SQLException {
        String query = "SELECT * "
                + "FROM \"ShoppingItem\" "
                + "WHERE CartID = ? AND ProductID = ?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        CartModel cart = (CartModel) cartOperation.getCart().get();
        Integer cartId = cart.getCart_id();
        p.setInt(1,cartId);
        p.setInt(2, productId);
        ResultSet rest = p.executeQuery();
        Optional<ShoppingItemModel> shoppingItem = Optional.empty();
        if (rest.next()) {
            ProductOperations productOperations = new ProductOperations();
            shoppingItem = Optional.of(new ShoppingItemModel());
            shoppingItem.get().setQuantity(rest.getInt("Quantity"));
            shoppingItem.get().setCartID(cartId);
            shoppingItem.get().setProduct(productOperations.get(productId).get());
        }
        p.close();
        return shoppingItem;
    }
    @Override
    public boolean add(ShoppingItemModel item) throws SQLException {
        Optional<ShoppingItemModel> ResultItem = get(item.getProduct().getID());
        if(!ResultItem.isPresent())
            InsertQuery(item);
        else{
        Integer newQuantity = ResultItem.get().getQuantity()+1;
        ResultItem.get().setQuantity(newQuantity);
        update(ResultItem.get());}
        return true;
    }

    private boolean InsertQuery(ShoppingItemModel order) throws SQLException {
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
    public void update(ShoppingItemModel order) throws SQLException {
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
    public void delete(Integer ProductID) throws SQLException {
        String query = "DELETE FROM \"ShoppingItem\" "
                + "WHERE CartID=? AND ProductID=? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        Optional<CartModel> cart = cartOperation.getCart();
        if(!cart.isPresent())
            throw new SQLException("Product is not present");
        Integer cartId = cart.get().getCart_id();
        p.setInt(1,cartId);
        p.setInt(2,ProductID);
        p.executeUpdate();
        p.close();
    }
}
