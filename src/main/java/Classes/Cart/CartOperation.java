package Classes.Cart;
import Classes.DAO.IAddDAO;
import Classes.Product.ProductModel;
import Classes.ShoppingItem.ShoppingItemModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartOperation implements ICartOperation<CartModel,ShoppingItemModel>{

    private Integer userId;
    public CartOperation(Integer User_id){
        this.userId = User_id;
    }
    @Override
    public void EmptyCart() throws SQLException{
        Integer Cart_id = getCart().get().getCart_id();
        String query = "DELETE FROM ShoppingItem "
                + "WHERE CartID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Cart_id);
        p.executeUpdate();
        p.close();
    }
    @Override
    public Optional<CartModel> getCart() throws SQLException{
        String query = "SELECT CartID "
                + "FROM  Cart "
                + "WHERE UserID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,userId);
        ResultSet rest = p.executeQuery();
        Optional<CartModel> cart = Optional.empty();
        if(rest.next()){
            cart = Optional.of(new CartModel());
        cart.get().setUser_id(userId);
        cart.get().setCart_id(rest.getInt(1));
            }
        p.close();
        return cart;
    }

    public boolean add(CartModel cart) throws SQLException {
        Optional<CartModel> ResultCart = getCart();
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


   public List<ShoppingItemModel> getAll() throws SQLException{
        Integer Cart_id = getCart().get().getCart_id();
        String query = "SELECT Quantity,Name,Cost,Image,ID "
                + "FROM ShoppingItem join products p on p.ID = ShoppingItem.ProductID "
                + "WHERE CartID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Cart_id);
        ResultSet rest = p.executeQuery();
        List<ShoppingItemModel> shoppingItems = new ArrayList<>();
        while (rest.next()){
            ProductModel product = new ProductModel.Builder(rest.getString("Name"))
                    .setImage(rest.getString("Image"))
                    .setCost(rest.getFloat("Cost"))
                    .setId(rest.getInt("ID"))
                    .build();
            ShoppingItemModel item = new ShoppingItemModel(product,Cart_id);
            item.setQuantity(rest.getInt(1));
            shoppingItems.add(item);
        }
        p.close();
        return shoppingItems;
    }

}