package Classes.Pattern;

import Classes.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class TestInsertOrder implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Order TestOrder = new Order();
        ProductOperations productOperations = new ProductOperations();
        Product TestProduct = productOperations.get(5).get();
        TestOrder.setQuantity(1);
        TestOrder.setProduct(TestProduct);
        User user = (User) request.getSession().getAttribute("user");
        TestOrder.setUser_id(user.getId());
        OrdersOperations ordersOperations = new OrdersOperations();
        ordersOperations.add(TestOrder);
        request.getSession().setAttribute("ShoppingList",ordersOperations.getCart(user.getId()));

        return "Homepage";
    }
}
