package Classes.ShoppingItem;

import Classes.Cart.CartOperation;
import Classes.FrontController.Action;
import Classes.User.UserModel;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 16177215)
public class DeleteShoppingItem implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            int id = Integer.parseInt(request.getParameter("id"));
            UserModel user = (UserModel) request.getSession().getAttribute("user");
            CartOperation cartOperation = new CartOperation(user.getId());
            ShoppingItemOperations itemOperations = new ShoppingItemOperations(cartOperation);
            itemOperations.delete(id);
            request.getSession().setAttribute("ShoppingList", cartOperation.getAll());
            return "/UserPages/Cart";
    }
}
