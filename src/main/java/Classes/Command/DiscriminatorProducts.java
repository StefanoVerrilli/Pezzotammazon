package Classes.Command;

import Classes.Product.ProductModel;
import Classes.Product.ProductsPageLogic;
import Classes.User.AccessLevels;

import java.util.List;

/**
 * Implementa {@link DiscriminatorInterface} e rimanda alla pagina dei prodotti specifica a seconda dell'{@link AccessLevels} dell'utente.
 * @see ProductsPageLogic
 * @see ProductModel
 */

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
