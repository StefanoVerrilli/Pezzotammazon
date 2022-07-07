package Classes.Product;

import Classes.Command.DiscriminatorInterface;
import Classes.Command.DiscriminatorProducts;
import Classes.Command.Dispatcher;
import Classes.Command.Invoker;
import Classes.FrontController.Action;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Suggestion.SuggestionModel;
import Classes.Suggestion.SuggestionOperation;
import Classes.User.AccessLevels;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsPageLogic implements Action {

    private List<ProductModel> product_list;
    private UserModel user;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        getAllProductsFromDatabase();

        request.getSession().setAttribute("products_table",product_list);

        getUserInformationFromSession(request);

        if(user.getAccessType() == AccessLevels.User)
            getSuggestionsAndAddtoSession(request);

        return getStringLinkToAppropriatePage();
    }

    private String getStringLinkToAppropriatePage() throws SQLException {
        DiscriminatorProducts discriminator = new DiscriminatorProducts(product_list);
        Invoker invoker = new Invoker(new Dispatcher(discriminator,user));
        return  invoker.executeOperation();
    }

    private void getAllProductsFromDatabase() throws SQLException {
        ProductOperations productOperations = new ProductOperations(new ProductCategoriesOperations());
        this.product_list = productOperations.getAll();
    }

    private void getUserInformationFromSession(HttpServletRequest request) {
        this.user = (UserModel) request.getSession().getAttribute("user");
    }

    private void getSuggestionsAndAddtoSession(HttpServletRequest request) throws SQLException {
        SuggestionOperation suggestionOperation = new SuggestionOperation();
        List<SuggestionModel> product_suggestions;
        try{
           product_suggestions = suggestionOperation.getAll(user.getId());
        } catch (SQLException exception) {
           product_suggestions = new ArrayList<>();
        }
        request.getSession().setAttribute("product_suggestions", product_suggestions);
    }
}
