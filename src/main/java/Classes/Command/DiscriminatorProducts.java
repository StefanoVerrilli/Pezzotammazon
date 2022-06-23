package Classes.Command;

import Classes.Models.Product;

import java.util.List;

public class DiscriminatorProducts implements DiscriminatorInterface{

    private List<Product> products;
    public DiscriminatorProducts(List<Product> list){
        this.products = list;
    }

    @Override
    public String UserPages() {
        return products != null ? "UserProducts" : "Homepage";
    }

    @Override
    public String AdminPages() {
        return products != null ? "ProductsTable" : "Homepage";
    }
}
