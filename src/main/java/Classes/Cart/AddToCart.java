package Classes.Cart;

import Classes.Product.ProductOperations;
import Classes.FrontController.Action;
import Classes.ShoppingItem.ShoppingItemOperations;
import Classes.Product.ProductModel;
import Classes.ShoppingItem.ShoppingItemModel;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddToCart implements Action {
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
        ProductModel newProduct = productOperations.get(id).get();
        ShoppingItemModel newOrder = new ShoppingItemModel(newProduct,cartOperation.getCart().get().getCart_id());
        shoppingItemOperations.add(newOrder);
        request.getSession().setAttribute("ShoppingList",cartOperation.getAll());
        return "/UserPages/Cart";
    }
}
