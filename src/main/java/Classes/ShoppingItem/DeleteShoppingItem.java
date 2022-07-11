package Classes.ShoppingItem;

import Classes.Cart.CartModel;
import Classes.Cart.CartOperations;
import Classes.Cart.ICartOperations;
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
            ICartOperations<CartModel,ShoppingItemModel> cartOperation = new CartOperations();
            IShoppingItemOperations<ShoppingItemModel> itemOperations =
            new ShoppingItemOperations(cartOperation);
            itemOperations.delete(id,user.getId());
            request.getSession().setAttribute("ShoppingList", cartOperation.getAll(user.getId()));
            return "/UserPages/Cart";
    }
}
