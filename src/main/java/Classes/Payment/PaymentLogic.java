package Classes.Payment;
import Classes.Cart.CartOperation;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.Cart.CartModel;
import Classes.OrderCollection.OrderCollection;
import Classes.Payment.Strategy.IPayMethod;
import Classes.Payment.Strategy.IPaymentFactory;
import Classes.Payment.Strategy.Payment;
import Classes.Payment.Strategy.PaymentFactory;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getParameter("payment_type");
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        PaymentFactory factory = new PaymentFactory(request);
        Payment Payment;
        if((Payment = factory.PaymentMethod(method)) == null) {
            request.getSession().setAttribute("error", "Invalid payment information.");
            return "/UserPages/Pay";
        }
        if(!Payment.Pay())
            request.getSession().setAttribute("error","Transaction failed");

        OrderCollectionOperations orderCollectionOperations =
        new OrderCollectionOperations(new CartOperation());
        OrderCollection orderCollection = new OrderCollection();
        orderCollection.setUser_ID(user.getId());
        orderCollection.setTimestamp(Date.valueOf(java.time.LocalDate.now()));
        orderCollectionOperations.add(orderCollection);
        orderCollectionOperations.AddSingleOrders(user.getId());

       // TODO: Aggiungere decremento amount

        EmptyCartWrapper(user.getId());
        return "/Homepage";
    }


    private void EmptyCartWrapper(int User_id) throws SQLException{
        CartOperation cartOperation = new CartOperation();
        Optional<CartModel> cart = cartOperation.get(User_id);
        if(cart.isPresent())
            cartOperation.EmptyCart(cart.get());
    }
}
