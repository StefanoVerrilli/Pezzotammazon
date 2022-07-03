package Classes.DataAnalysis;

import Classes.Cart.CartOperation;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.Product.ProductModel;
import Classes.User.UserModel;
import Classes.User.UsersOperations;
import Classes.OrderCollection.OrderCollection;
import Classes.DataAnalysis.UserAnalysis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderElaboration implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsersOperations usersOperations = new UsersOperations();
        Integer inputID = Integer.valueOf(request.getParameter("id"));
        System.out.println(inputID);
        OrderCollectionOperations orderCollectionOperations =
        new OrderCollectionOperations(new CartOperation(inputID));
        List<OrderCollection> collection = orderCollectionOperations.getAll(inputID);
        UserAnalysis analysis = new UserAnalysis(inputID,collection);
        Map<Integer,Integer> result = analysis.getPurchasePerCategory();
        for(Integer CatID : result.keySet()){
            System.out.println("Category ID" + CatID);
            System.out.println(result.get(CatID));
        }
        Map.Entry<Integer,Integer> maxEntry = analysis.getMaxMap(result);
        Optional<UserModel> user = usersOperations.get(inputID);
        if(maxEntry != null){
            List<ProductModel> entry = analysis.getSuggestions(maxEntry.getKey());
            request.getSession().setAttribute("suggestions", analysis.getSuggestions(maxEntry.getKey()));
            }
        return "/AdminPages/UserSuggestion";
    }
}
