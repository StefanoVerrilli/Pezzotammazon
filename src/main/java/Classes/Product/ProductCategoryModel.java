package Classes.Product;

public class ProductCategoryModel {

    private Integer categoryID;
    private String categoryDescription;


    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public ProductCategoryModel(Integer categoryID, String categoryDescription) {
        this.categoryID = categoryID;
        this.categoryDescription = categoryDescription;
    }
}
