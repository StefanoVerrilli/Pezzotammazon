package Classes.User.ChainofChecks;

import Classes.Cart.CartModel;
import Classes.Cart.CartOperations;
import Classes.Cart.ICartOperations;
import Classes.ShoppingItem.ShoppingItemModel;
import Classes.User.AccessLevels;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;

public class CreateCartHandler extends Handler{
    @Override
    public String handle(HttpServletRequest request) throws Exception {
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        if(user.getAccessType() == AccessLevels.Admin)
            next.handle(request);
        ICartOperations<CartModel, ShoppingItemModel> cartOperation = new CartOperations();
        CartModel cart = new CartModel();
        cart.setUser_id(user.getId());
        if(cartOperation.add(cart))
            return next.handle(request);
        else
            throw new RuntimeException("Error during creation of the cart");
    }
}
