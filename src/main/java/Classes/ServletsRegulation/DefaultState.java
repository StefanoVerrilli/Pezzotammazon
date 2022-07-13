package Classes.ServletsRegulation;

import Classes.FrontController.Actions;
import Classes.Product.ProductFetch;
import Classes.Product.ProductsPageLogic;
import Classes.User.Access.LogIn;
import Classes.User.Access.LogOut;
import Classes.User.Access.Register;

/**
 * Stato di default dell'utente.
 */

public class DefaultState implements State {

    @Override
    public void LoadLink() {
        Actions.putAction("GET/FetchProduct.do", new ProductFetch());
        Actions.putAction("POST/Register.do", new Register());
        Actions.putAction("POST/LogIn.do", new LogIn());
        Actions.putAction("GET/LogOut.do", new LogOut());
        Actions.putAction("GET/ProductsTable.do", new ProductsPageLogic());
    }
}
