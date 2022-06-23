package Classes.Pattern;

import Classes.DAO.CartOperation;
import Classes.DAO.ProductOperations;
import Classes.DAO.ShoppingItemOperations;
import Classes.Models.Cart;
import Classes.Models.Product;
import Classes.Models.ShoppingItem;
import Classes.Models.User;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@MultipartConfig(maxFileSize = 16177215)
public class ChangeCost implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int quantity = Integer.parseInt(request.getParameter("orderQuantity"));
        int id = Integer.parseInt(request.getParameter("orderId"));
        User user = (User) request.getSession().getAttribute("user");
        CartOperation cartOperation = new CartOperation();
        Optional<Cart> cart = cartOperation.get(user.getId());
        ShoppingItemOperations ordersOperations = new ShoppingItemOperations();
        ProductOperations productOperations = new ProductOperations();
        ShoppingItem item = new ShoppingItem();
        item.setCartID(cart.get().getCart_id());
        Optional<Product> product = productOperations.get(id);
        item.setProduct(product.get());
        item.setQuantity(quantity);
        ordersOperations.update(item);
        request.getSession().setAttribute("ShoppingList",cartOperation.getAll(cart.get().getCart_id()));
        return "Cart";
    }
}
