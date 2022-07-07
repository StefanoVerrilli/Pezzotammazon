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
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductModel;
import Classes.Product.ProductOperations;
import Classes.ShoppingItem.ShoppingItemModel;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserModel user = (UserModel) request.getSession().getAttribute("user");

        try {
            payOrderBySessionInformation(request);
        } catch (Exception exception) {
            request.getSession().setAttribute("error", exception.toString());
            return "/UserPages/Pay";
        }

        CartOperation cartOperation = new CartOperation();


        decrementProductsAmount(cartOperation, user);

        OrderCollectionOperations orderCollectionOperations =
        new OrderCollectionOperations(cartOperation);
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
        Optional<CartModel> cart = cartOperation.get(User_id);
        if(cart.isPresent())
            cartOperation.EmptyCart(cart.get());
    }

    private void payOrderBySessionInformation(HttpServletRequest request) throws Exception {
        String method = request.getParameter("payment_type");
        PaymentFactory factory = new PaymentFactory(request);
        Payment Payment;
        if((Payment = factory.PaymentMethod(method)) == null) {
            throw new Exception("Transaction failed");
        }
        if(!Payment.Pay()) {
            request.getSession().setAttribute("error", "Transaction failed");
            throw new Exception("Transaction failed");
        }
    }

    private void decrementProductsAmount(CartOperation operation, UserModel user) throws Exception {
        List<ShoppingItemModel> orderedItems = operation.getAll(user.getId());
        ProductOperations productOperations = new ProductOperations(new ProductCategoriesOperations());
        for(ShoppingItemModel item : orderedItems) {
            ProductModel product = productOperations.get(item.getProduct().getID()).get();
            Integer amountOrdered = item.getQuantity();

            try {
                decrementProductIstanceAmount(product, productOperations, amountOrdered);
            } catch (Exception e) {
                operation.delete(item.getCartID(), item.getProduct().getID());
            }
        }
    }

    private void decrementProductIstanceAmount(ProductModel product, ProductOperations operations, Integer amountOrdered) throws Exception {
        if((product.getAmount() - amountOrdered)  >= 0) {
            product.setAmount(product.getAmount() - amountOrdered);
            System.out.println(product.getCategory().getCategoryID());
            operations.update(product);
            System.out.println("FRATM");
        } else {
            System.out.println("CIAO");
            throw new Exception("Product can't be purchased");
        }
    }
}
