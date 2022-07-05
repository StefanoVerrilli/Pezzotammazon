package Classes.DataAnalysis.DatasetPreparation;

import Classes.Adapter.GSONAdapter;
import Classes.Adapter.IAdapter;
import Classes.Cart.CartOperation;
import Classes.Clustering.EuclideanDistance;
import Classes.Clustering.KMeans;
import Classes.Clustering.Record;
import Classes.Clustering.centroid;
import Classes.ConcreteHashAlg;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollection;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KmeansImp implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsersOperations usersOperations = new UsersOperations(new ConcreteHashAlg());
        List<UserModel> users = usersOperations.getAll();
        DataSetElaboration elaboration = new DataSetElaboration();
        OrderCollectionOperations orderCollectionOperations = new OrderCollectionOperations(new CartOperation());
        List<Record> records = new ArrayList<>();
        for(UserModel user : users){
            List<OrderCollection> orders = orderCollectionOperations.getAll(user.getId());
            records.add(elaboration.getData(user,orders));
        }
        KMeans means = new KMeans(new EuclideanDistance());
        Map<centroid, List<Record>> result =  means.fit(records,2,2000);
        IAdapter<centroid,List<Record>> adapter = new GSONAdapter();
        adapter.DataToJSON(result);
        return "/Homepage";
    }
}
