package Classes.OrderCollection;

import Classes.Cart.CartModel;
import Classes.Cart.ICartOperation;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetDAO;
import Classes.Order.Order;
import Classes.Order.OrderOperations;
import Classes.OrderCollection.OrderCollection;
import Classes.ShoppingItem.ShoppingItemModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderCollectionOperations implements IGetDAO<OrderCollection>, IAddDAO<OrderCollection>
{
    private final ICartOperation cartOperation;
    public OrderCollectionOperations(ICartOperation cartOperation){
        this.cartOperation = cartOperation;
    }
@Override
    public Optional<OrderCollection> get(Integer userId) throws SQLException {
        String query = "SELECT OrderID,Timestamp "
                + "FROM \"Order\" "
                + "WHERE UserID = ? "
                + "ORDER BY OrderID DESC LIMIT 1 ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,userId);
        ResultSet rest = p.executeQuery();
        Optional<OrderCollection> Collection = Optional.empty();
        if(rest.next()) {
            Collection = Optional.of(new OrderCollection());
            Collection.get().setCollectionID(rest.getInt(1));
            Collection.get().setTimestamp(rest.getDate(2));
        }
        p.close();
        return Collection;
    }

    @Override
    public boolean add(OrderCollection collection) throws SQLException {
        String query = "INSERT INTO \"Order\" (UserID, Timestamp) "
                + "VALUES (?,?) ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,collection.getUser_ID());
        p.setDate(2, collection.getTimestamp());
        p.executeUpdate();
        p.close();
        return true;
        }

    public boolean AddSingleOrders(int User_id) throws SQLException {
        CartModel Cart = (CartModel) cartOperation.getCart().get();
        System.out.println("id: " + Cart.getCart_id());
        System.out.println("collection id: " + get(User_id).get().getCollectionID());
        OrderOperations orderOperations = new OrderOperations(get(User_id).get().getCollectionID());
        List<ShoppingItemModel> shoppingItems = cartOperation.getAll();
        for(ShoppingItemModel item: shoppingItems){
            Order order = new Order(get(User_id).get().getCollectionID(),item);
            orderOperations.add(order);
        }
        return true;
    }


    public List<OrderCollection> getAll(int User_id) throws SQLException {
        List<OrderCollection> orderCollectionList = new ArrayList<>();
        String query = "SELECT OrderID,TimeStamp "
                + "FROM \"Order\" "
                + "WHERE UserID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1, User_id);
        ResultSet resultSet = p.executeQuery();
        while (resultSet.next()) {
            OrderCollection orderCollection = new OrderCollection();
            orderCollection.setUser_ID(User_id);
            orderCollection.setCollectionID(resultSet.getInt(1));
            orderCollection.setTimestamp(resultSet.getDate(2));
            orderCollectionList.add(orderCollection);
        }
        return orderCollectionList;
    }
}