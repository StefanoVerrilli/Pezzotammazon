package Classes.ShoppingItem;

import Classes.Cart.CartModel;
import Classes.Cart.CartOperations;
import Classes.Cart.ICartOperations;
import Classes.Product.IProductOperations;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductOperations;
import Classes.FrontController.Action;
import Classes.Product.ProductModel;
import Classes.User.UserModel;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@MultipartConfig(maxFileSize = 16177215)
public class ChangeCost implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int quantity = Integer.parseInt(request.getParameter("orderQuantity"));
        int id = Integer.parseInt(request.getParameter("orderId"));
        UserModel user = (UserModel) request.getSession().getAttribute("user");

        ICartOperations<CartModel,ShoppingItemModel> cartOperation = new CartOperations();
        IProductOperations<ProductModel> productOperations =
        new ProductOperations(new ProductCategoriesOperations());
        IShoppingItemOperations<ShoppingItemModel> ordersOperations =
         new ShoppingItemOperations(cartOperation);

        Optional<ProductModel> product = productOperations.get(id);
        if(product.isEmpty())
            throw new RuntimeException("No such item exist");
        Optional<CartModel> cart = cartOperation.get(user.getId());
        if(cart.isEmpty())
            throw new RuntimeException("No such cart");

        ShoppingItemModel item = new ShoppingItemModel(product.get(),cart.get().getCart_id());
        item.setQuantity(quantity);
        ordersOperations.update(item);
        request.getSession().setAttribute("ShoppingList",cartOperation.getAll(user.getId()));
        return "/UserPages/Cart";
    }
}
