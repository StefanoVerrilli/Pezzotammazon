package Classes.User.ChainofChecks;

import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductModel;
import Classes.Product.ProductOperations;
import Classes.Suggestion.SuggestionModel;
import Classes.Suggestion.SuggestionOperation;
import Classes.User.AccessLevels;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadDataHandler extends Handler {

    private List<ProductModel> product_list;
    private UserModel user;
    @Override
    public String handle(HttpServletRequest request) throws Exception {
        getAllProductsFromDatabase();

        request.getSession().setAttribute("products_table",product_list);

        getUserInformationFromSession(request);

        if(user.getAccessType() == AccessLevels.User)
            getSuggestionsAndAddtoSession(request);

        return next.handle(request);
    }

    private void getAllProductsFromDatabase() throws SQLException {
        ProductOperations productOperations = new ProductOperations(new ProductCategoriesOperations());
        this.product_list = productOperations.getAll();
    }

    private void getUserInformationFromSession(HttpServletRequest request) {
        this.user = (UserModel) request.getSession()
                                        .getAttribute("user");
    }

    private void getSuggestionsAndAddtoSession(HttpServletRequest request) {
        SuggestionOperation suggestionOperation = new SuggestionOperation();
        List<SuggestionModel> product_suggestions;
        try {
            product_suggestions = suggestionOperation.getAll(user.getId());
        } catch (SQLException exception) {
            product_suggestions = new ArrayList<>();
        }
        request.getSession()
                .setAttribute("product_suggestions", product_suggestions);
    }
}
