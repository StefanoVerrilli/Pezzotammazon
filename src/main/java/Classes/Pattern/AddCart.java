package Classes.Pattern;

import Classes.DAO.CartOperation;
import Classes.DAO.ProductOperations;
import Classes.DAO.ShoppingItemOperations;
import Classes.Models.Cart;
import Classes.Models.Product;
import Classes.Models.ShoppingItem;
import Classes.Models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddCart implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        if(id != null){
            ProductOperations productOperations = new ProductOperations();
            CartOperation cartOperation = new CartOperation();
            ShoppingItemOperations shoppingItemOperations = new ShoppingItemOperations();
            User user = (User) request.getSession().getAttribute("user");

            Optional<Cart> cart = cartOperation.get(user.getId());
            Product newProduct = productOperations.get(id).get();
            ShoppingItem newOrder = new ShoppingItem();
            newOrder.setProduct(newProduct);
            newOrder.setCartID(cart.get().getCart_id());

            shoppingItemOperations.add(newOrder);
            request.getSession().setAttribute("ShoppingList",cartOperation.getAll(cart.get().getCart_id()));
            return "/UserPages/Cart";
        }else{
            request.getSession().setAttribute("error","Invalid Product, please retry");
            return "UserProducts";
        }
    }
}
