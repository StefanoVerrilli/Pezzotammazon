package Classes.State;

import Classes.Pattern.*;

public class Context {

    private State state;

    public Context(){
        this.state = null;
    }

    public void changeState(State state){
        this.state = state;
    }
    public void LoadAdminLinks(){
        Actions.putAction("GET/delete.do",new delete());
        Actions.putAction("POST/EditAction.do",new EditAction());
        Actions.putAction("POST/Insert.do",new Insert());
    }

    public void LoadUserLinks(){
        Actions.putAction("POST/ChangeCost.do",new ChangeCost());
        Actions.putAction("POST/DeleteOrder.do",new DeleteOrder());
        Actions.putAction("GET/CartLogic.do",new CartLogic());
        Actions.putAction("GET/AddCart.do",new AddCart());
    }

    public void LoadDefaultLinks(){
        Actions.putAction("GET/FetchProduct.do",new FetchProduct());
        Actions.putAction("POST/Register.do",new Register());
        Actions.putAction("POST/LogIn.do", new LogIn());
        Actions.putAction("GET/LogOut.do",new LogOut());
        Actions.putAction("GET/ProductsTable.do",new ProductsPageLogic());
    }
}