package Classes.ServletsRegulation;

import Classes.Cart.AddToCart;
import Classes.Cart.CartLogic;
import Classes.FrontController.Actions;
import Classes.Payment.PaymentLogic;
import Classes.Payment.PaymentPageLoad;
import Classes.ShoppingItem.ChangeCost;
import Classes.ShoppingItem.DeleteShoppingItem;

public class UserState implements State{

    /**
     * Imposta le azioni disponibili per il cliente.
     */
    @Override
    public void LoadLink() {
        Actions.putAction("GET/PaymentPageLoad.do", new PaymentPageLoad());
        Actions.putAction("POST/ChangeCost.do", new ChangeCost());
        Actions.putAction("POST/DeleteOrder.do", new DeleteShoppingItem());
        Actions.putAction("GET/CartLogic.do", new CartLogic());
        Actions.putAction("GET/AddCart.do", new AddToCart());
        Actions.putAction("POST/PaymentLogic.do", new PaymentLogic());
    }
}
