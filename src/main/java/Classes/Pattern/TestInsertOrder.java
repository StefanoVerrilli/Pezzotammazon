package Classes.Pattern;

import Classes.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class TestInsertOrder implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Order TestOrder = new Order();
        TestOrder.setName("Amico");
        TestOrder.setQuantity(1);
        TestOrder.setCost(300);
        TestOrder.setID(1);
        TestOrder.UpdateSubTotal();
        List<Order> ShoppingList = new ArrayList<>();
        ShoppingList.add(TestOrder);
        request.getSession().setAttribute("ShoppingList",ShoppingList);
        return "Homepage";
    }
}
