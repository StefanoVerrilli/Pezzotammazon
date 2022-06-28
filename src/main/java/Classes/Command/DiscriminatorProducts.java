package Classes.Command;

import Classes.Product.ProductModel;

import java.util.List;

public class DiscriminatorProducts implements DiscriminatorInterface{

    private List<ProductModel> products;
    public DiscriminatorProducts(List<ProductModel> list){
        this.products = list;
    }

    @Override
    public String UserPages() {
        return products != null ? "/UserPages/UserProducts" : "/Homepage";
    }

    @Override
    public String AdminPages() {
        return products != null ? "/AdminPages/ProductsTable" : "/Homepage";
    }
}
