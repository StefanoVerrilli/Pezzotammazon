package Classes.Pattern;

import Classes.Order;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@MultipartConfig(maxFileSize = 16177215)
public class ChangeCost implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int quantity = Integer.parseInt(request.getParameter("orderQuantity"));
        int id = Integer.parseInt(request.getParameter("orderId"));
        List<Order> ShoppingList = (List<Order>) request.getSession().getAttribute("ShoppingList");
        for(Order element : ShoppingList){
            if(element.getID() == id){
                element.setQuantity(quantity);
                element.UpdateSubTotal();
            }
        }
        request.setAttribute("ShoppingList",ShoppingList);
        return "Cart";
    }
}
