package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingItem implements DAO<Order>{

    public void EmptyOrders(int User_id) throws SQLException{
        String query = "DELETE FROM \"Order\" "
                + "WHERE User_ID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,User_id);
        p.executeUpdate();
        p.close();
    }

    @Override
    public Optional<Order> get(int id) throws SQLException {
        String query = "SELECT * "
                + "FROM \"ShoppingItem\" "
                + "WHERE CartID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,id);
        ResultSet rest = p.executeQuery();
        Order myOrder = new Order();
        myOrder.setQuantity(rest.getInt("Quantity"));
        myOrder.setID(id);
        myOrder.setUser_id(rest.getInt("User_ID"));
        ProductOperations productOperations = new ProductOperations();
        myOrder.setProduct(productOperations.get(rest.getInt("Item_ID")).get());
        p.close();
        return Optional.of(myOrder);
    }

    public List<Order> getCart(int User_ID) throws SQLException {
        List<Order> ShoppingList = new ArrayList<>();
        String query = "SELECT Quantity,Name,Cost,Item_ID,Order_ID "
                + "FROM \"Order\" join products p on p.ID = \"Order\".Item_ID "
                + "WHERE User_ID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,User_ID);
        ResultSet rest = p.executeQuery();
        while (rest.next()){
            Order tempOrder = new Order();
            tempOrder.setQuantity(rest.getInt(1));
            tempOrder.setUser_id(User_ID);
            tempOrder.setID(rest.getInt("Order_ID"));
            Product tempProduct = new Product();
            tempProduct.setName(rest.getString("Name"));
            tempProduct.setCost(rest.getFloat("Cost"));
            tempProduct.setID(rest.getInt("Item_ID"));
            tempOrder.setProduct(tempProduct);
            ShoppingList.add(tempOrder);
        }
        p.close();
        return ShoppingList;
    }

    @Override
    public boolean add(Order order) throws SQLException {
        Optional<Integer> doesExist = Optional.ofNullable(CheckOrderExist(order.getUser_id(), order.getProduct().getID()));
        if(doesExist.isPresent()){
            Optional<Order> newOrder = get(doesExist.get());
            int newQuantity = newOrder.get().getQuantity()+1;
            newOrder.get().setQuantity(newQuantity);
            update(newOrder.get());
        }else{
        String query = "INSERT INTO \"Order\" (quantity, user_id, item_id) "
                + "VALUES (?,?,?)";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,order.getQuantity());
        p.setInt(2,order.getUser_id());
        p.setInt(3,order.getProduct().getID());
        p.executeUpdate();
        p.close();}

        return true;
    }

    private Integer CheckOrderExist(int user_id, int item_id)throws SQLException{
        String query ="SELECT Order_ID "
                + "FROM \"Order\" "
                + "WHERE user_id=? AND item_id=?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,user_id);
        p.setInt(2,item_id);
        ResultSet rest = p.executeQuery();
        Integer orderId = null;
        if(rest.next()){orderId = rest.getInt(1);}
        p.close();
        return orderId;
    }

    @Override
    public void update(Order order) throws SQLException {
        String query = "UPDATE \"ShoppingItem\" "
                + "SET Quantity=? "
                + "WHERE CartID=? AND ProductID=?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,order.getQuantity());
        p.setInt(2,order.getID());
        p.executeUpdate();
        p.close();
    }

    @Override
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
