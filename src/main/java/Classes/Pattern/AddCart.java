package Classes.Pattern;

import Classes.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCart implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        if(id != null){
            ProductOperations productOperations = new ProductOperations();
            Product newProduct = productOperations.get(id).get();
            Order newOrder = new Order();
            newOrder.setProduct(newProduct);
            User user = (User) request.getSession().getAttribute("user");
            newOrder.setUser_id(user.getId());
            OrdersOperations ordersOperations = new OrdersOperations();
            ordersOperations.add(newOrder);
            request.getSession().setAttribute("ShoppingList",ordersOperations.getCart(user.getId()));
            return "Cart";
        }else{
            request.getSession().setAttribute("error","Invalid Product, please retry");
            return "UserProducts";
        }
    }
}
