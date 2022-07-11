package Classes.Suggestion;


import Classes.Product.ProductCategory.ProductCategoryModel;
import Classes.Product.ProductModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuggestionOperation implements ISuggestionOperations<SuggestionModel> {

    @Override
    public boolean add(SuggestionModel suggestion) throws SQLException {
        String query = "INSERT INTO Suggestion(user_id, product_id) "
            + "VALUES (?,?) ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,suggestion.getUserID());
        p.setInt(2,suggestion.getProductID());
        p.executeUpdate();
        p.close();
        return true;
    }


    public List<SuggestionModel> getAll(Integer userId) throws SQLException {
        String query = "SELECT product_id,Name,Description,Amount,Cost,CategoryID,Image,CategoryDescription "
        +   "FROM Suggestion join products on Suggestion.product_id = products.ID "
        +   "join ProductCategories on products.Category = ProductCategories.CategoryID "
        +   "WHERE User_id = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,userId);
        ResultSet rest = p.executeQuery();
        List<SuggestionModel> suggestionModelList = new ArrayList<>();
        while (rest.next()){
            ProductCategoryModel category =
            new ProductCategoryModel(rest.getInt("CategoryID"),
            rest.getString("CategoryDescription"));
            ProductModel productModel = new ProductModel.Builder(rest.getString("Name"))
                                        .setId(rest.getInt("product_id"))
                                        .setAmount(rest.getInt("Amount"))
                                        .setCost(rest.getFloat("Cost"))
                                        .setDesc(rest.getString("Description"))
                                        .setImage(rest.getString("Image"))
                                        .setCategory(category)
                                        .build();
            SuggestionModel suggestion = new SuggestionModel(userId,productModel);
            suggestionModelList.add(suggestion);
        }
        p.close();
        return suggestionModelList;
    }
    @Override
    public void delete(Integer userId,Integer productID)throws SQLException{
        String query = "DELETE FROM Suggestion "
            + "WHERE User_id =? AND Product_id =? ";
            PreparedStatement p = myDb.getConnection().prepareStatement(query);
            p.setInt(1,userId);
            p.setInt(2,productID);
            p.executeUpdate();
            p.close();
    }
}
