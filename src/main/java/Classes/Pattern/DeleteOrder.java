package Classes.Pattern;

import Classes.Models.Cart;
import Classes.DAO.CartOperation;
import Classes.DAO.ShoppingItemOperations;
import Classes.Models.User;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@MultipartConfig(maxFileSize = 16177215)
public class DeleteOrder implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = (User) request.getSession().getAttribute("user");
            CartOperation cartOperation = new CartOperation();
            Optional<Cart> cart = cartOperation.get(user.getId());
            ShoppingItemOperations itemOperations = new ShoppingItemOperations(cart.get().getCart_id());
            itemOperations.delete(id);
            request.getSession().setAttribute("ShoppingList", cartOperation.getAll(cart.get().getCart_id()));
            return "/UserPages/Cart";
    }
}
