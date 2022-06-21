package Classes.Pattern;

import Classes.ShoppingItem;
import Classes.User;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 16177215)
public class DeleteOrder implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            ShoppingItem ordersOperations = new ShoppingItem();
            ordersOperations.delete(id);
            User user = (User) request.getSession().getAttribute("user");
            request.getSession().setAttribute("ShoppingList", ordersOperations.getCart(user.getId()));
            return "Cart";
    }
}
