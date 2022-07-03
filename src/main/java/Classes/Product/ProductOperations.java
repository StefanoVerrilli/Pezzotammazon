package Classes.Product;

import Classes.DAO.*;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductOperations implements IProductDAO<ProductModel> {

    private final ProductCategoriesOperations categoriesOperation;

    public ProductOperations(ProductCategoriesOperations categoriesOperation) {
        this.categoriesOperation = categoriesOperation;
    }

    public Optional<ProductModel> get(Integer id) throws SQLException {
        String query = "SELECT * "
                + "FROM products join ProductCategories on products.Category = ProductCategories.CategoryID "
                + "WHERE ID = ? ";
        PreparedStatement p = DAO.myDb.getConnection().prepareStatement(query);
        p.setInt(1,id);
        ResultSet rest = p.executeQuery();

        ProductCategoryModel category = new ProductCategoryModel(rest.getInt("Category"),
                rest.getString("CategoryDescription"));

        ProductModel product = new ProductModel.Builder(rest.getString("Name"))
                                       .setImage(rest.getString("Image"))
                                       .setCost(rest.getFloat("Cost"))
                                       .setId(rest.getInt("ID"))
                                       .setAmount(rest.getInt("Amount"))
                                       .setDesc(rest.getString("Description"))
                                       .setCategory(category)
                                       .build();
        p.close();
        return Optional.of(product);
    }

    public List<ProductModel> getAll() throws SQLException {
        List result = new ArrayList();
        String query = "SELECT * "
                + "FROM products join ProductCategories on products.Category = ProductCategories.CategoryID ";
        Statement stat = (Statement) DAO.myDb.getConnection().createStatement();
        ResultSet rest = stat.executeQuery(query);
        while(rest.next()){

            ProductCategoryModel category = new ProductCategoryModel(rest.getInt("Category"),
                                            rest.getString("CategoryDescription"));

            ProductModel product = new ProductModel.Builder(rest.getString("Name"))
                                           .setImage(rest.getString("Image"))
                                           .setCost(rest.getFloat("Cost"))
                                           .setId(rest.getInt("ID"))
                                           .setAmount(rest.getInt("Amount"))
                                           .setDesc(rest.getString("Description"))
                                           .setCategory(category)
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
        p.setInt(5,product.getCategory().getCategoryID());
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
        p.setInt(5,product.getCategory().getCategoryID());
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

    @Override
    public List<ProductModel> getAllByCategory(Integer Category) throws SQLException {
        String query = "SELECT * "
                + "FROM products "
                + "WHERE Category = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Category);
        ResultSet rest = p.executeQuery();
        List<ProductModel> elements = new ArrayList<>();

        ProductCategoryModel category = categoriesOperation.get(Category).get();

        while (rest.next()){
            ProductModel product = new ProductModel.Builder(rest.getString("Name"))
                                           .setImage(rest.getString("Image"))
                                           .setCost(rest.getFloat("Cost"))
                                           .setId(rest.getInt("ID"))
                                           .setAmount(rest.getInt("Amount"))
                                           .setDesc(rest.getString("Description"))
                                           .setCategory(category)
                                           .build();
            elements.add(product);
        }
        return elements;
    }


    public List<ProductModel> getAllByCategory(String CategoryDescription) throws SQLException {
        String query = "SELECT Name,Image,Cost,ID,Amount,Description,Category "
                + "FROM products join ProductCategories on products.Category = ProductCategories.CategoryID "
                + "WHERE CategoryDescription = ? ";

        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,CategoryDescription);
        ResultSet rest = p.executeQuery();
        List<ProductModel> elements = new ArrayList<>();
        ProductCategoryModel categoryObject = new ProductCategoryModel(rest.getInt("Category"), CategoryDescription);
        ProductCategoryModel category = categoriesOperation.get(categoryObject.getCategoryID()).get();

        while (rest.next()){
            ProductModel product = new ProductModel.Builder(rest.getString("Name"))
                    .setImage(rest.getString("Image"))
                    .setCost(rest.getFloat("Cost"))
                    .setId(rest.getInt("ID"))
                    .setAmount(rest.getInt("Amount"))
                    .setDesc(rest.getString("Description"))
                    .setCategory(category)
                    .build();
            elements.add(product);
        }
        return elements;

    }

}


