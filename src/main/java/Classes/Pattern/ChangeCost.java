package Classes.Pattern;

import Classes.Order;
import Classes.ShoppingItem;
import Classes.User;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 16177215)
public class ChangeCost implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int quantity = Integer.parseInt(request.getParameter("orderQuantity"));
        System.out.println(quantity);
        int id = Integer.parseInt(request.getParameter("orderId"));
        ShoppingItem ordersOperations = new ShoppingItem();
        Order order = ordersOperations.get(id).get();
        order.setQuantity(quantity);
        ordersOperations.update(order);
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("ShoppingList",ordersOperations.getCart(user.getId()));
        return "Cart";
    }
}
