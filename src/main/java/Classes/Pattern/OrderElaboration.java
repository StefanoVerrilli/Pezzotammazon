package Classes.Pattern;

import Classes.DAO.CartOperation;
import Classes.DAO.OrderCollectionOperations;
import Classes.DAO.OrderOperations;
import Classes.DAO.UsersOperations;
import Classes.Models.Order;
import Classes.Models.OrderCollection;
import Classes.Models.UserAnalysis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class OrderElaboration implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsersOperations usersOperations = new UsersOperations();
        Integer User_ID = 1;
        OrderCollectionOperations orderCollectionOperations = new OrderCollectionOperations(new CartOperation(User_ID));
        List<OrderCollection> collection = orderCollectionOperations.getAll(User_ID);
        UserAnalysis analysis = new UserAnalysis(User_ID,collection);
        Map<String,Integer> result = analysis.getPurchasePerCategory();
        for(String Name : result.keySet()){
            System.out.println(Name);
            System.out.println(result.get(Name));
        }
        return "/Homepage";
    }
}
