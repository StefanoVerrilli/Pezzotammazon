package Classes.Pattern;

import Classes.OrdersOperations;
import Classes.Strategy.IPay;
import Classes.Strategy.PayBancomat;
import Classes.Strategy.PayCard;
import Classes.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymantLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getParameter("mode");
        IPay payment = null;
        OrdersOperations ordersOperations = new OrdersOperations();
        User user = (User) request.getSession().getAttribute("user");
        switch (method){
            case "Bancomat":
                payment = new PayBancomat();
            case "CreditCard":
                payment = new PayCard();
            default:
                request.getSession().setAttribute("error","PaymentFailed");
        }
        if(payment != null){
            payment.Pay();
            ordersOperations.EmptyOrders(user.getId());
        }
        return "Homepage";
    }
}
