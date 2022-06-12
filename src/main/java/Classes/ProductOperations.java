package Classes;

import com.sun.org.apache.xpath.internal.operations.Or;

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
                    + "FROM products ";
            Statement stat = (Statement) myDb.getConnection().createStatement();
            ResultSet rest = stat.executeQuery(query);
            ResultSetMetaData metaData = rest.getMetaData();
            int ColsCount = metaData.getColumnCount();
            System.out.println("ciao");
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
    public static void AddProduct(Product myProduct) throws SQLException{
        int result;
        String query = "INSERT INTO products (Name,Description,Amount,Cost,Category,Image) "
                + "VALUES(?,?,?,?,?,?)";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,myProduct.getName());
        p.setString(2,myProduct.getDesc());
        p.setInt(3,myProduct.getAmount());
        p.setFloat(4,myProduct.getCost());
        p.setString(5,myProduct.getCategory());
        p.setString(6,myProduct.getImage());
        result = p.executeUpdate();
        p.close();
    }

    public static void ModifyProduct(Product myProduct) throws SQLException{
        String query = "UPDATE products "
                + "SET Name = ?, Description=?,Amount=?,Cost=?,Category=? ,Image=? "
                + "WHERE ID=? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,myProduct.getName());
        p.setString(2,myProduct.getDesc());
        p.setInt(3,myProduct.getAmount());
        p.setFloat(4,myProduct.getCost());
        p.setString(5,myProduct.getCategory());
        p.setString(6,myProduct.getImage());
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
        NewProduct.setImage(rest.getString("Image"));
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

    public static List<Order> getCart(String email) throws SQLException {
            List<Order> ShoppingList = new ArrayList<>();
            String query = "SELECT Quantity,Name,Cost "
                    + "FROM \"Order\" join products p on p.ID = \"Order\".Item_ID "
                    + "WHERE User_ID = ? ";
            PreparedStatement p = myDb.getConnection().prepareStatement(query);
            p.setString(1,email);
            ResultSet rest = p.executeQuery();
            while (rest.next()){
                Order tempOrder = new Order();
                tempOrder.setQuantity(rest.getInt(1));
                tempOrder.setName(rest.getString(2));
                tempOrder.setCost(rest.getFloat(3));
                ShoppingList.add(tempOrder);
            }
            return ShoppingList;

    }
}
