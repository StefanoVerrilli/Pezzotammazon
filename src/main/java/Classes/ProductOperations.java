package Classes;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductOperations implements DAO<Product>{

    @Override
    public Optional<Product> get(int id) throws SQLException {
        String query = "SELECT * "
                + "FROM products "
                + "WHERE ID = ? ";
        PreparedStatement p = DAO.myDb.getConnection().prepareStatement(query);
        p.setInt(1,id);
        ResultSet rest = p.executeQuery();
        Product NewProduct = new Product();
        NewProduct.setID(rest.getInt("ID"));
        NewProduct.setName(rest.getString("Name"));
        NewProduct.setAmount(rest.getInt("Amount"));
        NewProduct.setDesc(rest.getString("Description"));
        NewProduct.setCategory(rest.getString("Category"));
        NewProduct.setCost(rest.getFloat("Cost"));
        NewProduct.setImage(rest.getString("Image"));
        p.close();
        return Optional.of(NewProduct);
    }

    public List<Product> getAll() throws SQLException {
        List result = new ArrayList();
        String query = "SELECT * "
                + "FROM products ";
        Statement stat = (Statement) DAO.myDb.getConnection().createStatement();
        ResultSet rest = stat.executeQuery(query);
        ResultSetMetaData metaData = rest.getMetaData();
        while(rest.next()){
            Product CurrProduct = new Product();
            CurrProduct.setID(Integer.parseInt(rest.getString("ID")));
            CurrProduct.setName(rest.getString("Name"));
            CurrProduct.setDesc(rest.getString("Description"));
            CurrProduct.setAmount(Integer.parseInt(rest.getString("Amount")));
            CurrProduct.setCost(Float.parseFloat(rest.getString("Cost")));
            CurrProduct.setCategory(rest.getString("Category"));
            CurrProduct.setImage(rest.getString("Image"));
            result.add(CurrProduct);
        }
        return result;
    }

    @Override
    public boolean add(Product product) throws SQLException {
        int result;
        String query = "INSERT INTO products (Name,Description,Amount,Cost,Category,Image) "
                + "VALUES(?,?,?,?,?,?)";
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

    @Override
    public void update(Product product) throws SQLException {
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


    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM products "
                + "WHERE ID=? ";
        PreparedStatement p = DAO.myDb.getConnection().prepareStatement(query);
        p.setInt(1,id);
        p.executeUpdate();
        p.close();
    }
}
