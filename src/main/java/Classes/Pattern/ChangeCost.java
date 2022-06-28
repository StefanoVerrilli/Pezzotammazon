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
        CartOperation cartOperation = new CartOperation(user.getId());
        ProductOperations productOperations = new ProductOperations();
        ShoppingItemOperations ordersOperations = new ShoppingItemOperations(cartOperation);
        Optional<Product> product = productOperations.get(id);
        ShoppingItem item = new ShoppingItem(product.get(),cartOperation.getNow().get().getCart_id());
        item.setQuantity(quantity);
        ordersOperations.update(item);
        request.getSession().setAttribute("ShoppingList",cartOperation.getAll());
        return "/UserPages/Cart";
    }
}
