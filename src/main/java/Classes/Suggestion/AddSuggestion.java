package Classes.Suggestion;

import Classes.Exceptions.LogicException;
import Classes.FrontController.Action;
import Classes.Product.IProductOperations;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductModel;
import Classes.Product.ProductOperations;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@MultipartConfig(maxFileSize = 16177215)
public class AddSuggestion implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId  = Integer.valueOf(request.getParameter("userId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        List<ProductModel> suggestions =
        (List<ProductModel>) request.getSession().getAttribute("suggestions");
        suggestions.removeIf(e -> e.getID() == productId);
        request.getSession().setAttribute("suggestions",suggestions);
        ISuggestionOperations<SuggestionModel> suggestionOperation = new SuggestionOperation();
        IProductOperations<ProductModel> productOperations = new ProductOperations(new ProductCategoriesOperations());
        Optional<ProductModel> product = productOperations.get(productId);
        if(product.isEmpty())
            throw new LogicException(request,"error","NoProductFound");
        suggestionOperation.add(new SuggestionModel(userId,product.get()));
        return "";
    }
}
