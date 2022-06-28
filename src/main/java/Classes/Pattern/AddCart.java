package Classes.Pattern;

import Classes.Cart.CartOperation;
import Classes.DAO.ProductOperations;
import Classes.ShoppingItem.ShoppingItemOperations;
import Classes.Models.Product;
import Classes.ShoppingItem.ShoppingItemModel;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCart implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        if(id == null) {
            request.getSession().setAttribute("error", "Invalid Product, please retry");
            return "UserProducts";
        }
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        ProductOperations productOperations = new ProductOperations();
        CartOperation cartOperation = new CartOperation(user.getId());

        ShoppingItemOperations shoppingItemOperations = new ShoppingItemOperations(cartOperation);
        Product newProduct = productOperations.get(id).get();
        ShoppingItemModel newOrder = new ShoppingItemModel(newProduct,cartOperation.getCart().get().getCart_id());
        shoppingItemOperations.add(newOrder);
        request.getSession().setAttribute("ShoppingList",cartOperation.getAll());
        return "/UserPages/Cart";
    }
}
