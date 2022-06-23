package Classes.Pattern;

import Classes.DAO.CartOperation;
import Classes.DAO.OrderCollectionOperations;
import Classes.DAO.ShoppingItemOperations;
import Classes.Models.Cart;
import Classes.Models.OrderCollection;
import Classes.Models.User;
import Classes.Pattern.Action;
import Classes.Strategy.PaymentFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getParameter("payment_type");
        User user = (User) request.getSession().getAttribute("user");
        PaymentFactory factory = new PaymentFactory();
        factory.PaymentMethod(method).Pay();
        OrderCollectionOperations orderCollectionOperations = new OrderCollectionOperations();
        orderCollectionOperations.add(user.getId());
        orderCollectionOperations.AddSingleOrders(user.getId());
        EmptyCartWrapper(user.getId());
        return "/Homepage";
    }


    private void EmptyCartWrapper(int User_id) throws SQLException{
        CartOperation cartOperation = new CartOperation();
        ShoppingItemOperations ItemsOperations = new ShoppingItemOperations();
        Optional<Cart> cart = cartOperation.get(User_id);
        if(cart.isPresent())
            ItemsOperations.EmptyOrders(cart.get().getCart_id());

    }
}