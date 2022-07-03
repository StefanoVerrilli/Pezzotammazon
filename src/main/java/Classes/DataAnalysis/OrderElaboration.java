package Classes.DataAnalysis;

import Classes.Cart.CartOperation;
import Classes.Clustering.Record;
import Classes.ConcreteHashAlg;
import Classes.DataAnalysis.DatasetPreparation.DataSetElaboration;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
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
        Integer inputID = Integer.valueOf(request.getParameter("id"));
        OrderCollectionOperations orderCollectionOperations =
        new OrderCollectionOperations(new CartOperation(inputID));
        List<OrderCollection> collection = orderCollectionOperations.getAll(inputID);
        Optional<UserModel> user = usersOperations.get(inputID);
        DataSetElaboration dataSetElaboration = new DataSetElaboration();

        Record result = dataSetElaboration.getData(user.get(),collection);
        String Category = dataSetElaboration.MaxPurchaseCategory(result);
        request.getSession().setAttribute("suggestions",dataSetElaboration.getSuggestions(Category,collection));
        return "/AdminPages/UserSuggestion";
    }
}
