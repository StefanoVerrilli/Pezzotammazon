package Classes.SuggestionSystemFacede;

import Classes.Clustering.*;
import Classes.Clustering.Record;
import Classes.OrderCollection.IOrderCollectionOperations;
import Classes.OrderCollection.OrderCollectionModel;
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
    IOrderCollectionOperations orderOperations,
    Integer clusters,Integer iterations) throws SQLException {
        List<Record> recordList = new ArrayList<>();
        List<UserModel> users = DataPreparationFacade.getSuggestibleUsers(userOperation,orderOperations);
        for(UserModel user : users){
            List<OrderCollectionModel> orders = orderOperations.getAll(user.getId());
            recordList.add(DataPreparationFacade.getData(user, orders));
        }
        return ClusteringMethod.fit(recordList, clusters, iterations);
    }
}
