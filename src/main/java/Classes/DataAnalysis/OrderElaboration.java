package Classes.DataAnalysis;

import Classes.Cart.CartOperation;
import Classes.Clustering.Record;
import Classes.ConcreteHashAlg;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.SuggestionSystemFacede.DataAnalysisFacade;
import Classes.User.UserModel;
import Classes.User.UsersOperations;
import Classes.OrderCollection.OrderCollection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class OrderElaboration implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsersOperations usersOperations = new UsersOperations(new ConcreteHashAlg());
        Integer UserId = Integer.valueOf(request.getParameter("id"));
        OrderCollectionOperations orderCollectionOperations =
        new OrderCollectionOperations(new CartOperation());
        List<OrderCollection> collection = orderCollectionOperations.getAll(UserId);
        Optional<UserModel> user = usersOperations.get(UserId);
        if(!user.isPresent())
            throw new RuntimeException("User does not exist");
        DataAnalysisFacade facade = new DataAnalysisFacade();

        // TODO: Errore in questa classe che causa un 505 (getAllByCategory)
        request.getSession().setAttribute("suggestions", facade.getSuggestions(collection, user.get()));
        request.getSession().setAttribute("selected_user", user.get());

        // TODO: Reintegrare queste funzioni (utili per le statistiche)
        Record result = facade.getData(user.get(),collection);
        request.getSession().setAttribute("user_purchases_by_category", result.getFeatures());

         return "/AdminPages/UserSuggestion";
    }
}
