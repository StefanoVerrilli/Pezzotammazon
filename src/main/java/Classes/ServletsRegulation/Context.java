package Classes.ServletsRegulation;

import Classes.Cart.AddToCart;
import Classes.Cart.CartLogic;
import Classes.DataAnalysis.DatasetPreparation.KmeansImplementation;
import Classes.DataAnalysis.OrderElaboration;
import Classes.ShoppingItem.ChangeCost;
import Classes.DataAnalysis.UsersPageLogic;
import Classes.FrontController.*;
import Classes.Product.*;
import Classes.ShoppingItem.DeleteShoppingItem;
import Classes.Payment.PaymentLogic;
import Classes.Payment.PaymentPageLoad;
import Classes.Suggestion.AddSuggestion;
import Classes.User.Access.LogIn;
import Classes.User.Access.LogOut;
import Classes.User.Access.Register;

public class Context {

    private State state;

    public Context(State state){
        this.state = state;
    }

    public void changeState(State state){
        this.state = state;
    }
    public void LoadLinks(){
        this.state.LoadLink();
    }
}
