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

    private Integer userId;
    public CartOperation(Integer User_id){
        this.userId = User_id;
    }
    public void EmptyOrders(int Cart_id) throws SQLException{
        String query = "DELETE FROM ShoppingItem "
                + "WHERE CartID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Cart_id);
        p.executeUpdate();
        p.close();
    }

    public Optional<Cart> getNow() throws SQLException{
        String query = "SELECT CartID "
                + "FROM  Cart "
                + "WHERE UserID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,userId);
        ResultSet rest = p.executeQuery();
        Optional<Cart> cart = Optional.empty();
        if(rest.next()){
            cart = Optional.of(new Cart());
        cart.get().setUser_id(userId);
        cart.get().setCart_id(rest.getInt(1));
            }
        p.close();
        return cart;
    }

    @Override
    public Optional<Cart> get(Integer id) throws SQLException {
        return Optional.empty();
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


   public List<ShoppingItem> getAll() throws SQLException{
        Integer Cart_id = this.getNow().get().getCart_id();
        String query = "SELECT Quantity,Name,Cost,Image,ID "
                + "FROM ShoppingItem join products p on p.ID = ShoppingItem.ProductID "
                + "WHERE CartID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Cart_id);
        ResultSet rest = p.executeQuery();
        List<ShoppingItem> shoppingItems = new ArrayList<>();
        while (rest.next()){
            Product product = new Product();
            product.setImage(rest.getString("Image"));
            product.setCost(rest.getFloat("Cost"));
            product.setID(rest.getInt("ID"));
            product.setName(rest.getString("Name"));
            ShoppingItem item = new ShoppingItem(product,Cart_id);
            item.setQuantity(rest.getInt(1));
            shoppingItems.add(item);
        }
        p.close();
        return shoppingItems;
    }


    @Override
    public void update(Cart cart) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
