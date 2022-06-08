package Classes;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductOperations {

    private static final Database myDb = Database.getInstance();

    public static List<Product> GetProducts() throws SQLException{
            List result = new ArrayList();
            String query = "SELECT * "
                    + "FROM products";
            Statement stat = (Statement) myDb.getConnection().createStatement();
            ResultSet rest = stat.executeQuery(query);
            ResultSetMetaData metaData = rest.getMetaData();
            int ColsCount = metaData.getColumnCount();
            while(rest.next()){
                Product CurrProduct = new Product();
                CurrProduct.setID(Integer.parseInt(rest.getString("ID")));
                CurrProduct.setName(rest.getString("Name"));
                CurrProduct.setDesc(rest.getString("Description"));
                CurrProduct.setAmount(Integer.parseInt(rest.getString("Amount")));
                CurrProduct.setCost(Float.parseFloat(rest.getString("Cost")));
                CurrProduct.setCategory(rest.getString("Category"));
                result.add(CurrProduct);
            }
            return result;
    }
    public static void AddProduct(Product myProduct) throws SQLException{
        int result;
        String query = "INSERT INTO products (Name,Description,Amount,Cost,Category) "
                + "VALUES(?,?,?,?,?)";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,myProduct.getName());
        p.setString(2,myProduct.getDesc());
        p.setInt(3,myProduct.getAmount());
        p.setFloat(4,myProduct.getCost());
        p.setString(5,myProduct.getCategory());
        result = p.executeUpdate();
        p.close();
    }

    public static void ModifyProduct(Product myProduct) throws SQLException{
        String query = "UPDATE products "
                + "SET Name = ?, Description=?,Amount=?,Cost=?,Category=? "
                + "WHERE ID=? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,myProduct.getName());
        p.setString(2,myProduct.getDesc());
        p.setInt(3,myProduct.getAmount());
        p.setFloat(4,myProduct.getCost());
        p.setString(5,myProduct.getCategory());
        System.out.println("questo vede" + myProduct.getID());
        p.setInt(6,myProduct.getID());
        p.executeUpdate();
        p.close();
    }

    public static Product GetSpecificProduct(int ID) throws SQLException{
        String query = "SELECT * "
                + "FROM products "
                + "WHERE ID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,ID);
        ResultSet rest = p.executeQuery();
        Product NewProduct = new Product();
        NewProduct.setName(rest.getString("Name"));
        NewProduct.setAmount(rest.getInt("Amount"));
        NewProduct.setDesc(rest.getString("Description"));
        NewProduct.setCategory(rest.getString("Category"));
        NewProduct.setCost(rest.getFloat("Cost"));
        p.close();
        return NewProduct;
    }

    public static void DeleteProduct(int ID) throws SQLException{
        String query = "DELETE FROM products "
                + "WHERE ID=? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,ID);
        p.executeUpdate();
        p.close();
    }

    private static void FixAutoIncrement() throws SQLException{
        String query = "UPDATE products "
                + "SET ID = (@increment_value := @increment_value+ 1) "
                + "order by ID ";
        Statement stat = (Statement) myDb.getConnection().createStatement();
        stat.executeUpdate(query);
        stat.close();
    }
}
