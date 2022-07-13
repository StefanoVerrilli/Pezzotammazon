package Classes.Order;

import Classes.DAO.Database;
import Classes.Product.ProductCategory.ProductCategoryModel;
import Classes.Product.ProductModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Operazioni concrete sul database per l'ottenimento dei dati sulle ordinazioni eseguite.
 * @see OrderModel
 */

public class OrderOperations implements IOrderOperations<OrderModel> {

    private Database myDb = Database.getInstance();

    public boolean add(OrderModel order,Integer collectionID) throws SQLException {
        String query = "INSERT INTO ItemOrder(Quantity, OrderID, ProductID) "
                + "VALUES(?,?,?) ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1, order.getQuantity());
        p.setInt(2, collectionID);
        p.setInt(3, order.getItem().getID());
        p.executeUpdate();
        p.close();
        return true;
    }
    @Override
    public List<OrderModel> getAllByCategory(String Category,Integer collectionID) throws SQLException {
        String query = "SELECT Quantity,Name,Image,Description,ID,Amount,Cost,Category,CategoryDescription "
                + "FROM ItemOrder join products p on p.ID = ItemOrder.ProductID "
                + "join ProductCategories on p.Category = ProductCategories.CategoryID "
                + "WHERE OrderID = ? AND CategoryDescription = ?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1, collectionID);
        p.setString(2, Category);
        ResultSet rest = p.executeQuery();
        if(rest.isClosed())
            return new ArrayList<>();
        return getAllByCategoryResults(rest,collectionID);
    }


    private List<OrderModel> getAllByCategoryResults(ResultSet rest,Integer collectionID) throws SQLException {

        List<OrderModel> result = new ArrayList<>();
            ProductCategoryModel category = new ProductCategoryModel
            (rest.getInt("Category"), rest.getString("CategoryDescription"));

        while (rest.next()) {
            ProductModel product = new ProductModel
                    .Builder(rest.getString("Name"))
                    .setImage(rest.getString("Image"))
                    .setCost(rest.getFloat("Cost"))
                    .setId(rest.getInt("ID"))
                    .setAmount(rest.getInt("Amount"))
                    .setDesc(rest.getString("Description"))
                    .setCategory(category)
                    .build();
            OrderModel orderModel = new OrderModel(rest.getInt(1), product, collectionID);
            result.add(orderModel);
        }

        return result;
    }

    public List<OrderModel> getAll(Integer collectionID) throws SQLException {
        List<OrderModel> result = new ArrayList<>();
        String query = "SELECT Quantity,Name,Image,Description,ID,Amount,Cost,Category, CategoryDescription "
                               + "FROM ItemOrder join products p on p.ID = ItemOrder.ProductID "
                               + "join ProductCategories on p.Category = ProductCategories.CategoryID "
                               + "WHERE OrderID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1, collectionID);
        ResultSet rest = p.executeQuery();
        while (rest.next()) {
            ProductCategoryModel category =
                    new ProductCategoryModel(rest.getInt("Category"),
                            rest.getString("CategoryDescription"));

            ProductModel product = new ProductModel.Builder(rest.getString("Name"))
                                           .setImage(rest.getString("Image"))
                                           .setCost(rest.getFloat("Cost"))
                                           .setId(rest.getInt("ID"))
                                           .setAmount(rest.getInt("Amount"))
                                           .setDesc(rest.getString("Description"))
                                           .setCategory(category)
                                           .build();
            OrderModel orderModel = new OrderModel(rest.getInt(1), product, collectionID);
            result.add(orderModel);
        }
        return result;
    }

    }


