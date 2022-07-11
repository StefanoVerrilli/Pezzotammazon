package Classes.Cart;

import Classes.Product.IProductOperations;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductOperations;
import Classes.FrontController.Action;
import Classes.ShoppingItem.IShoppingItemOperations;
import Classes.ShoppingItem.ShoppingItemOperations;
import Classes.Product.ProductModel;
import Classes.ShoppingItem.ShoppingItemModel;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddToCart implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        if(id == 0) {
            request.getSession().setAttribute("error", "Invalid Product, please retry");
            return "UserProducts";
        }
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        IProductOperations<ProductModel> productOperations =
        new ProductOperations(new ProductCategoriesOperations());
        ICartOperations<CartModel,ShoppingItemModel> cartOperation = new CartOperations();

        IShoppingItemOperations<ShoppingItemModel> shoppingItemOperations =
        new ShoppingItemOperations(cartOperation);
        Optional<ProductModel> newProduct = productOperations.get(id);
        if(newProduct.isEmpty()){
            return "/Error/404";
        }
        ShoppingItemModel newOrder = new ShoppingItemModel(newProduct.get(),
        cartOperation.get(user.getId()).get().getCart_id());

        shoppingItemOperations.add(newOrder, user.getId());
        request.getSession().setAttribute("ShoppingList",cartOperation.getAll(user.getId()));
        return "/UserPages/Cart";
    }
}
