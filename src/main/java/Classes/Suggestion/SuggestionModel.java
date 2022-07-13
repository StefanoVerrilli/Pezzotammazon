package Classes.Suggestion;

import Classes.Product.ProductModel;

/**
 * Modello per i suggerimenti.
 */
public class SuggestionModel {

    private Integer User_id;
    private ProductModel suggestedProduct;

    public SuggestionModel(Integer user_id,ProductModel suggestedProduct) {
        this.User_id = user_id;
        this.suggestedProduct = suggestedProduct;
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

    public void setProduct(ProductModel suggestedProduct) {
        this.suggestedProduct = suggestedProduct;
    }
}
