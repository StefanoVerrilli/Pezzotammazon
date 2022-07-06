package Classes.SuggestionSystemFacede;

import Classes.Clustering.*;
import Classes.Clustering.Record;
import Classes.OrderCollection.IOrderCollectionDAO;
import Classes.OrderCollection.OrderCollection;
import Classes.User.IUserOperation;
import Classes.User.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClusteringFacade {

private final DataAnalysisFacade DataPreparationFacade = new DataAnalysisFacade();
private final Clustering ClusteringMethod;

    public ClusteringFacade(Clustering clusteringMethod) {
        ClusteringMethod = clusteringMethod;
    }

    public Map<centroid, List<Record>> ExecuteClustering(IUserOperation userOperation,
    IOrderCollectionDAO orderDAO,
    Integer clusters,Integer iterations) throws SQLException {
        List<Record> recordList = new ArrayList<>();
        List<UserModel> users = userOperation.getAll();
        for(UserModel user : users){
            List<OrderCollection> orders = orderDAO.getAll(user.getId());
            recordList.add(DataPreparationFacade.getData(user, orders));
        }
        Map<centroid, List<Record>> result = ClusteringMethod.fit(recordList, clusters, iterations);
        return result;
    }
}
