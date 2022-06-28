package Classes.Product;

import Classes.DAO.*;
import Classes.Product.ProductModel;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductOperations implements IGetDAO<ProductModel>, IAddDAO<ProductModel>, IUpdateDAO<ProductModel>,
        IDeleteDAO {

    public Optional<ProductModel> get(Integer id) throws SQLException {
        String query = "SELECT * "
                + "FROM products "
                + "WHERE ID = ? ";
        PreparedStatement p = DAO.myDb.getConnection().prepareStatement(query);
        p.setInt(1,id);
        ResultSet rest = p.executeQuery();
        ProductModel product = new ProductModel.Builder(rest.getString("Name"))
                                       .setImage(rest.getString("Image"))
                                       .setCost(rest.getFloat("Cost"))
                                       .setId(rest.getInt("ID"))
                                       .setAmount(rest.getInt("Amount"))
                                       .setDesc(rest.getString("Description"))
                                       .setCategory(rest.getString("Category"))
                                       .build();
        p.close();
        return Optional.of(product);
    }

    public List<ProductModel> getAll() throws SQLException {
        List result = new ArrayList();
        String query = "SELECT * "
                + "FROM products ";
        Statement stat = (Statement) DAO.myDb.getConnection().createStatement();
        ResultSet rest = stat.executeQuery(query);
        while(rest.next()){
            System.out.println(rest.getString("Name"));
            ProductModel product = new ProductModel.Builder(rest.getString("Name"))
                                           .setImage(rest.getString("Image"))
                                           .setCost(rest.getFloat("Cost"))
                                           .setId(rest.getInt("ID"))
                                           .setAmount(rest.getInt("Amount"))
                                           .setDesc(rest.getString("Description"))
                                           .setCategory("Category")
                                           .build();
            result.add(product);
        }
        return result;
    }

    public boolean add(ProductModel product) throws SQLException {
        int result;
        String query = "INSERT INTO products (Name,Description,Amount,Cost,Category,Image) "
                + "VALUES(?,?,?,ROUND(?,2),?,?)";
        PreparedStatement p = DAO.myDb.getConnection().prepareStatement(query);
        p.setString(1,product.getName());
        p.setString(2,product.getDesc());
        p.setInt(3,product.getAmount());
        p.setFloat(4,product.getCost());
        p.setString(5,product.getCategory());
        p.setString(6,product.getImage());
        result = p.executeUpdate();
        p.close();
        return true;
    }

    public void update(ProductModel product) throws SQLException {
        String query = "UPDATE products "
                + "SET Name = ?, Description=?,Amount=?,Cost=?,Category=? ,Image=? "
                + "WHERE ID=? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,product.getName());
        p.setString(2,product.getDesc());
        p.setInt(3,product.getAmount());
        p.setFloat(4,product.getCost());
        p.setString(5,product.getCategory());
        p.setString(6,product.getImage());
        p.setInt(7,product.getID());
        p.executeUpdate();
        p.close();
    }


    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM products "
                + "WHERE ID=? ";
        PreparedStatement p = DAO.myDb.getConnection().prepareStatement(query);
        p.setInt(1,id);
        p.executeUpdate();
        p.close();
    }
}
