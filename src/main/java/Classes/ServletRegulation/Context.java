package Classes.ServletRegulation;

import Classes.Cart.AddToCart;
import Classes.Cart.CartLogic;
import Classes.DataAnalysis.OrderElaboration;
import Classes.ShoppingItem.ChangeCost;
import Classes.DataAnalysis.UsersPageLogic;
import Classes.FrontController.*;
import Classes.Product.*;
import Classes.ShoppingItem.DeleteShoppingItem;
import Classes.Payment.PaymentLogic;
import Classes.Payment.PaymentPageLoad;
import Classes.User.Access.LogIn;
import Classes.User.Access.LogOut;
import Classes.User.Access.Register;

public class Context {

    private State state;

    public Context(){
        this.state = null;
    }

    public void changeState(State state){
        this.state = state;
    }
    public void LoadAdminLinks(){
        Actions.putAction("GET/delete.do",new ProductDelete());
        Actions.putAction("POST/EditAction.do",new ProductEdit());
        Actions.putAction("POST/Insert.do",new ProductInsert());
        Actions.putAction("GET/UsersPageLogic.do",new UsersPageLogic());
        Actions.putAction("GET/orderElaboration.do",new OrderElaboration());
    }

    public void LoadUserLinks(){
        Actions.putAction("GET/PaymentPageLoad.do",new PaymentPageLoad());
        Actions.putAction("POST/ChangeCost.do",new ChangeCost());
        Actions.putAction("POST/DeleteOrder.do",new DeleteShoppingItem());
        Actions.putAction("GET/CartLogic.do",new CartLogic());
        Actions.putAction("GET/AddCart.do",new AddToCart());
        Actions.putAction("POST/PaymentLogic.do",new PaymentLogic());
    }

    public void LoadDefaultLinks(){
        Actions.putAction("GET/FetchProduct.do",new ProductFetch());
        Actions.putAction("POST/Register.do",new Register());
        Actions.putAction("POST/LogIn.do", new LogIn());
        Actions.putAction("GET/LogOut.do",new LogOut());
        Actions.putAction("GET/ProductsTable.do",new ProductsPageLogic());
    }
}
