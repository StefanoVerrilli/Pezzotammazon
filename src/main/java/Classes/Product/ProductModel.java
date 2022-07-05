package Classes.Product;

import Classes.Product.ProductCategory.ProductCategoryModel;

public class ProductModel {


    private int ID;
    private String name;
    private String desc;
    private int amount;
    private float cost;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setCategory(ProductCategoryModel category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private ProductCategoryModel category;

    private String image;

    private ProductModel(){
    }

    public static class Builder{

        private final String name;
        private String desc ="";
        private Integer amount= 0;
        private Float cost = 0.0f;
        private String image;
        private Integer id = 0;
        private ProductCategoryModel category;
        public Builder(String name){
        this.name = name;
        }


        public Builder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder setAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder setCost(float cost) {
            this.cost = cost;
            return this;
        }
        public Builder setCategory(ProductCategoryModel Category){
            this.category = Category;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ProductModel build(){
            ProductModel productModel = new ProductModel();
            productModel.name = this.name;
            productModel.amount = this.amount;
            productModel.cost = this.cost;
            productModel.desc = this.desc;
            productModel.ID = this.id;
            productModel.image = this.image;
            productModel.category = this.category;
            return productModel;
        }
    }

    public ProductCategoryModel getCategory() {
        return category;
    }


    public float getCost() {
        return cost;
    }


    public int getAmount() { return amount; }


    public String getDesc() {
        return desc;
    }


    public String getName() {
        return name;
    }


    public int getID() {
        return ID;
    }

    public String getImage() {
        return image;
    }


    public boolean decimalIsZero(float number) {return (number % 1) == 0;}
}
