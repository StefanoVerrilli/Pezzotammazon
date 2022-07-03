package Classes.Payment;
import Classes.Cart.CartOperation;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.Cart.CartModel;
import Classes.OrderCollection.OrderCollection;
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
        if(!factory.PaymentMethod(method).Pay()) {
<<<<<<< HEAD
            request.getSession().setAttribute("invalid_payment", "Invalid payment information.");
=======
        System.out.println("scossa");
            request.setAttribute("invalid_payment", "Invalid payment information.");
>>>>>>> 2dbf56e9b641c4526bf83b8b92803c9a3842524d
            return "/UserPages/Pay";
        }
        OrderCollectionOperations orderCollectionOperations =
        new OrderCollectionOperations(new CartOperation(user.getId()));
        OrderCollection orderCollection = new OrderCollection();
        orderCollection.setUser_ID(user.getId());
        orderCollection.setTimestamp(Date.valueOf(java.time.LocalDate.now()));
        orderCollectionOperations.add(orderCollection);
        orderCollectionOperations.AddSingleOrders(user.getId());
        EmptyCartWrapper(user.getId());
        return "/Homepage";
    }


    private void EmptyCartWrapper(int User_id) throws SQLException{
        CartOperation cartOperation = new CartOperation(User_id);
        Optional<CartModel> cart = cartOperation.getCart();
        if(cart.isPresent())
            cartOperation.EmptyCart();
    }
}