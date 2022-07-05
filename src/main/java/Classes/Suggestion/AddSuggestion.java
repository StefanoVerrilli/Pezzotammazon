package Classes.Suggestion;

import Classes.FrontController.Action;
import Classes.Product.ProductModel;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@MultipartConfig(maxFileSize = 16177215)
public class AddSuggestion implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId  = Integer.valueOf(request.getParameter("userId"));
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        List<ProductModel> suggestions = (List<ProductModel>) request.getSession().getAttribute("suggestions");
        suggestions.removeIf(e -> e.getID() == productId);
        request.getSession().setAttribute("suggestions",suggestions);
        SuggestionOperation suggestionOperation = new SuggestionOperation();
        suggestionOperation.add(new SuggestionModel(userId,productId));
        return "/Homepage";
    }
}
