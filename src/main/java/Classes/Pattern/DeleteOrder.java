package Classes.Pattern;

import Classes.Order;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@MultipartConfig(maxFileSize = 16177215)
public class DeleteOrder implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            List<Order> tempList = (List<Order>) request.getSession().getAttribute("ShoppingList");
            int id = Integer.parseInt(request.getParameter("id"));
            tempList.removeIf(element -> element.getID() == id);
            request.getSession().setAttribute("ShoppingList", tempList);
            return "Cart";
    }
}
