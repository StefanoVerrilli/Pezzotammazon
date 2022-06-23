package Classes.DAO;
import Classes.Models.Cart;
import Classes.Models.Product;
import Classes.Models.ShoppingItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartOperation implements DAO<Cart> {

    public void EmptyOrders(int Cart_id) throws SQLException{
        String query = "DELETE FROM ShoppingItem "
                + "WHERE CartID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Cart_id);
        p.executeUpdate();
        p.close();
    }

    public Optional<Cart> get(int User_id) throws SQLException{
        String query = "SELECT CartID "
                + "FROM  Cart "
                + "WHERE UserID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,User_id);
        ResultSet rest = p.executeQuery();
        Optional<Cart> cart = Optional.empty();
        if(rest.next()){
            cart = Optional.of(new Cart());
        cart.get().setUser_id(User_id);
        cart.get().setCart_id(rest.getInt(1));
            }
        p.close();
        return cart;
    }

    @Override
    public boolean add(Cart cart) throws SQLException {
        Optional<Cart> ResultCart = get(cart.getUser_id());
        if(!ResultCart.isPresent()){
        String query = "INSERT INTO Cart (UserID) "
                + "VALUES (?)";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,cart.getUser_id());
        p.executeUpdate();
        p.close();
        }
        return true;
    }


    public List<ShoppingItem> getAll(int Cart_id) throws SQLException{
        String query = "SELECT Quantity,Name,Cost,Image,ID,Cart.CartID "
                + "FROM Cart join ShoppingItem i on Cart.CartID = i.CartID "
                + "join products p on p.ID = i.ProductID "
                + "WHERE Cart.CartID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Cart_id);
        ResultSet rest = p.executeQuery();
        List<ShoppingItem> shoppingItems = new ArrayList<>();
        while (rest.next()){
            ShoppingItem item = new ShoppingItem();
            item.setQuantity(rest.getInt("Quantity"));
            item.setCartID(rest.getInt("CartID"));
            Product product = new Product();
            product.setImage(rest.getString("Image"));
            product.setCost(rest.getFloat("Cost"));
            product.setID(rest.getInt("ID"));
            product.setName(rest.getString("Name"));
            item.setProduct(product);
            shoppingItems.add(item);
        }
        p.close();
        return shoppingItems;
    }


    @Override
    public void update(Cart cart) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
