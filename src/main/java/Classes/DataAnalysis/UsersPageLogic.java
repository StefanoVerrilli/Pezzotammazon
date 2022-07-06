package Classes.DataAnalysis;

import Classes.Cart.CartOperation;
import Classes.ConcreteHashAlg;
import Classes.FrontController.Action;
import Classes.Order.OrderOperations;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.SuggestionSystemFacede.DataAnalysisFacade;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersPageLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DataAnalysisFacade dataAnalysisFacade = new DataAnalysisFacade();
        List<UserModel> userModelList = dataAnalysisFacade.getSuggestibleUsers(
        new UsersOperations(new ConcreteHashAlg()),
        new OrderCollectionOperations(new CartOperation()));

        request.getSession().setAttribute("UsersList",userModelList);
        return "/AdminPages/UsersPage";
    }
}
