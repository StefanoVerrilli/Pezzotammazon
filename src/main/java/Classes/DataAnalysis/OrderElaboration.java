package Classes.DataAnalysis;

import Classes.Cart.CartOperations;
import Classes.Clustering.Record;
import Classes.Exceptions.LogicException;
import Classes.OrderCollection.IOrderCollectionOperations;
import Classes.User.Hashing.ConcreteHashAlg;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.SuggestionSystemFacede.DataAnalysisFacade;
import Classes.User.IUserOperation;
import Classes.User.UserModel;
import Classes.User.UsersOperations;
import Classes.OrderCollection.OrderCollectionModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

public class OrderElaboration implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        IUserOperation<UserModel> usersOperations = new UsersOperations(new ConcreteHashAlg());
        int UserId = Integer.parseInt(request.getParameter("id"));

        IOrderCollectionOperations<OrderCollectionModel> orderCollectionOperations =
        new OrderCollectionOperations(new CartOperations());

        List<OrderCollectionModel> collection = orderCollectionOperations.getAll(UserId);
        Optional<UserModel> user = usersOperations.get(UserId);
        if(user.isEmpty())
            throw new LogicException(request,"error","User does not exist");
        DataAnalysisFacade facade = new DataAnalysisFacade();

        request.getSession().setAttribute("suggestions", facade.getSuggestions(collection, user.get()));
        request.getSession().setAttribute("selected_user", user.get());

        Record result = facade.getData(user.get(),collection);
        result.getFeatures().entrySet().removeIf(e ->  e.getValue() == 0);
        request.getSession().setAttribute("user_purchases_by_category", result.getFeatures().entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)));

         return "/AdminPages/UserSuggestion";
    }
}
