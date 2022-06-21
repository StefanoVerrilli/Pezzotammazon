package Classes.Pattern;

import Classes.OrdersOperations;
import Classes.Strategy.PaymentFactory;
import Classes.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymantLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getParameter("Option");
        OrdersOperations ordersOperations = new OrdersOperations();
        User user = (User) request.getSession().getAttribute("user");
        PaymentFactory factory = new PaymentFactory();
        System.out.println(method);
        factory.PaymentMethod(method).Pay();
        ordersOperations.EmptyOrders(user.getId());
        return "Homepage";
    }
}
