package Classes.Product.ProductCategory;

import Classes.DAO.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductCategoriesOperations implements IProductCategoryDAO<ProductCategoryModel> {


    @Override
    public boolean add(ProductCategoryModel category) throws SQLException {
        int result;
        String query = "INSERT INTO ProductsCategory (CategoryID,CategoryDescription) "
                + "VALUES(?,?)";
        PreparedStatement p = DAO.myDb.getConnection().prepareStatement(query);
        p.setInt(1, category.getCategoryID());
        p.setString(2, category.getCategoryDescription());
        p.executeUpdate();
        p.close();
        return true;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM ProductCategories "
                + "WHERE CategoryID = ? ";
        PreparedStatement p = DAO.myDb.getConnection().prepareStatement(query);
        p.setInt(1,id);
        p.executeUpdate();
        p.close();
    }

    @Override
    public Optional<ProductCategoryModel> get(Integer Id) throws SQLException {
        String query = "SELECT * "
                + "FROM ProductCategories "
                + "WHERE CategoryID = ? ";
        PreparedStatement p = DAO.myDb.getConnection().prepareStatement(query);
        p.setInt(1,Id);
        ResultSet results = p.executeQuery();
        ProductCategoryModel category = new ProductCategoryModel(results.getInt("CategoryID"),
        results.getString("CategoryDescription"));
        p.close();
        return Optional.of(category);
    }

    public List<ProductCategoryModel> getAll() throws SQLException {
        List<ProductCategoryModel> catList = new ArrayList<>();
        String query = "SELECT * "
                + "FROM ProductCategories ";
        Statement stat = (Statement) DAO.myDb.getConnection().createStatement();
        ResultSet result = stat.executeQuery(query);
        while(result.next()){
            ProductCategoryModel category = new ProductCategoryModel(result.getInt("CategoryID"),
            result.getString("CategoryDescription"));
            catList.add(category);
        }
        stat.close();
        return catList;
    }

    @Override
    public void update(ProductCategoryModel category) throws SQLException {
        String query = "UPDATE ProductCategories "
                + "SET CategoryID = ?, CategoryDescription = ?"
                + "WHERE CategoryID = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,category.getCategoryID());
        p.setString(2,category.getCategoryDescription());
        p.executeUpdate();
        p.close();
    }
}