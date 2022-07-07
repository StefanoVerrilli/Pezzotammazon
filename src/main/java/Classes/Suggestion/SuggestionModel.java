package Classes.Suggestion;

import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductModel;
import Classes.Product.ProductOperations;

import java.sql.SQLException;

public class SuggestionModel {

    private Integer User_id;
    private ProductModel suggestedProduct;

    public SuggestionModel(Integer user_id,Integer suggestedProductID) throws SQLException {
        this.User_id = user_id;
        this.setProduct(suggestedProductID);
    }


    public Integer getUserID() {
        return User_id;
    }

    public void setUser(Integer user_id) {
        User_id = user_id;
    }

    public Integer getProductID() {
        return suggestedProduct.getID();
    }
    public ProductModel getProduct() {return suggestedProduct;}

    public void setProduct(Integer suggestedProductID) throws SQLException {
        ProductOperations productOperations = new ProductOperations(new ProductCategoriesOperations());
        this.suggestedProduct = productOperations.get(suggestedProductID).get();
    }
}
