package Classes.Pattern;

import Classes.DAO.CartOperation;
import Classes.DAO.OrderCollectionOperations;
import Classes.Models.Cart;
import Classes.Models.OrderCollection;
import Classes.Models.User;
import Classes.Payment.Strategy.PaymentFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getParameter("payment_type");
        User user = (User) request.getSession().getAttribute("user");
        PaymentFactory factory = new PaymentFactory(request);
        if(!factory.PaymentMethod(method).Pay()) {
            request.setAttribute("invalid_payment", "Invalid payment information.");
            return "/UserPages/Pay";
        }
        OrderCollectionOperations orderCollectionOperations = new OrderCollectionOperations();
        OrderCollection orderCollection = new OrderCollection();
        orderCollection.setUser_ID(user.getId());
        orderCollection.setTimestamp(Date.valueOf(java.time.LocalDate.now()));
        orderCollectionOperations.add(orderCollection);
        orderCollectionOperations.AddSingleOrders(user.getId());
        EmptyCartWrapper(user.getId());
        return "/Homepage";
    }


    private void EmptyCartWrapper(int User_id) throws SQLException{
        CartOperation cartOperation = new CartOperation();
        Optional<Cart> cart = cartOperation.get(User_id);
        if(cart.isPresent())
            cartOperation.EmptyOrders(cart.get().getCart_id());
    }
}