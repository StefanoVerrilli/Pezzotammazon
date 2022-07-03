package Classes.Order;

import Classes.DAO.IAddDAO;
import Classes.Order.Order;
import Classes.Product.ProductCategoryModel;
import Classes.Product.ProductModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderOperations implements IAddDAO<Order> {
    private Integer collectionID;
    public OrderOperations(Integer collectionID){
        this.collectionID = collectionID;
    }
    @Override
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
        String query = "SELECT Quantity,Name,Image,Description,ID,Amount,Cost,Category, CategoryDescription "
                + "FROM ItemOrder join products p on p.ID = ItemOrder.ProductID join p.Category = ProductCategories.CategoryID "
                + "WHERE OrderID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,this.collectionID);
        ResultSet rest = p.executeQuery();
        while (rest.next()){

            ProductCategoryModel category = new ProductCategoryModel(rest.getInt("Category"), rest.getString("CategoryDescription"));

            ProductModel product = new ProductModel.Builder(rest.getString("Name"))
                                           .setImage(rest.getString("Image"))
                                           .setCost(rest.getFloat("Cost"))
                                           .setId(rest.getInt("ID"))
                                           .setAmount(rest.getInt("Amount"))
                                           .setDesc(rest.getString("Description"))
                                           .setCategory(category)
                                           .build();
            Order order = new Order(rest.getInt(1),product,collectionID);
            result.add(order);
        }
        return result;
    }

}
