package Classes.Pattern;

import Classes.ShoppingItemOperations;
import Classes.Strategy.PaymentFactory;
import Classes.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymantLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getParameter("Option");
        ShoppingItemOperations ordersOperations = new ShoppingItemOperations();
        User user = (User) request.getSession().getAttribute("user");
        PaymentFactory factory = new PaymentFactory();
        System.out.println(method);
        factory.PaymentMethod(method).Pay();
        ordersOperations.EmptyOrders(user.getId());
        return "Homepage";
    }
}
