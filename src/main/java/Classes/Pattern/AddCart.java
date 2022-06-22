package Classes.Pattern;

import Classes.*;

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
            User user = (User) request.getSession().getAttribute("user");
            System.out.println("1");
            Optional<Cart> cart = cartOperation.get(user.getId());
            Product newProduct = productOperations.get(id).get();
            ShoppingItem newOrder = new ShoppingItem();
            newOrder.setProduct(newProduct);
            newOrder.setCartID(cart.get().getCart_id());
            System.out.println("santo 2");
            cartOperation.AddItem(newOrder);
            request.getSession().setAttribute("ShoppingList",cartOperation.getAll(cart.get().getCart_id()));
            return "Cart";
        }else{
            request.getSession().setAttribute("error","Invalid Product, please retry");
            return "UserProducts";
        }
    }
}
