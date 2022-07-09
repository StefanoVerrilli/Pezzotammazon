package Classes.Command;

import Classes.Product.ProductModel;

import java.util.List;

public class DiscriminatorProducts implements DiscriminatorInterface{

    private final List<ProductModel> products;
    public DiscriminatorProducts(List<ProductModel> list){
        this.products = list;
    }

    @Override
    public String UserPages() {
        return products != null ? "/UserPages/UserProducts" : "/Error/404";
    }

    @Override
    public String AdminPages() {
        return products != null ? "/AdminPages/ProductsTable" : "/Error/404";
    }
}
