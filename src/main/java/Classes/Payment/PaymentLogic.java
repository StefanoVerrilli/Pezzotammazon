package Classes.Payment;

<<<<<<< HEAD:src/main/java/Classes/Pattern/PaymentLogic.java
import Classes.DAO.CartOperation;
import Classes.DAO.OrderCollectionOperations;
import Classes.Models.Cart;
import Classes.Models.OrderCollection;
import Classes.Models.User;
import Classes.Payment.Strategy.PaymentFactory;
=======
import Classes.Cart.CartOperation;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.Cart.CartModel;
import Classes.OrderCollection.OrderCollection;
import Classes.User.UserModel;
>>>>>>> d1941a206d8c10ad35c924fdeadfe841875a3561:src/main/java/Classes/Payment/PaymentLogic.java

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getParameter("payment_type");
<<<<<<< HEAD:src/main/java/Classes/Pattern/PaymentLogic.java
        User user = (User) request.getSession().getAttribute("user");
        PaymentFactory factory = new PaymentFactory(request);
        if(!factory.PaymentMethod(method).Pay()) {
            request.setAttribute("invalid_payment", "Invalid payment information.");
            return "/UserPages/Pay";
        }
        OrderCollectionOperations orderCollectionOperations = new OrderCollectionOperations();
=======
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        PaymentFactory factory = new PaymentFactory();
        factory.PaymentMethod(method).Pay();
        OrderCollectionOperations orderCollectionOperations =
        new OrderCollectionOperations(new CartOperation(user.getId()));
>>>>>>> d1941a206d8c10ad35c924fdeadfe841875a3561:src/main/java/Classes/Payment/PaymentLogic.java
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