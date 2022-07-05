package Classes.Suggestion;

public class SuggestionModel {

    private Integer User_id;
    private Integer Product_id;

    public SuggestionModel(Integer user_id,Integer product_id){
        this.User_id = user_id;
        this.Product_id = product_id;
    }


    public Integer getUser_id() {
        return User_id;
    }

    public void setUser_id(Integer user_id) {
        User_id = user_id;
    }

    public Integer getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(Integer product_id) {
        Product_id = product_id;
    }
}
