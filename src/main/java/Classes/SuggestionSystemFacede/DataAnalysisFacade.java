package Classes.SuggestionSystemFacede;

import Classes.Clustering.Record;
import Classes.DataAnalysis.DatasetPreparation.DataSetElaboration;
import Classes.OrderCollection.IOrderCollectionOperations;
import Classes.OrderCollection.OrderCollectionModel;
import Classes.Product.ProductModel;
import Classes.User.IUserOperation;
import Classes.User.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataAnalysisFacade {

public List<ProductModel> getSuggestions(List<OrderCollectionModel> orderCollectionList,
 UserModel User) throws SQLException{
    DataSetElaboration dataSetElaboration = new DataSetElaboration();
    Record record = dataSetElaboration.getData(User, orderCollectionList);
    String preferredCategory = dataSetElaboration.MaxPurchaseCategory(record);
    return dataSetElaboration.getSuggestions(preferredCategory, orderCollectionList);
 }

public Record getData(UserModel user, List<OrderCollectionModel> orderCollectionList) throws SQLException{
    DataSetElaboration dataSetElaboration = new DataSetElaboration();
    return dataSetElaboration.getData(user,orderCollectionList);
}

public List<UserModel> getSuggestibleUsers(IUserOperation userOperation,
IOrderCollectionOperations orderCollection) throws SQLException {
    List<UserModel> users = userOperation.getAll();
    List<UserModel> result = new ArrayList<>();
    for(UserModel user : users){
        List<OrderCollectionModel> orderCollectionList = orderCollection.getAll(user.getId());
        Record record = getData(user,orderCollectionList);
        for(Integer isSuggestible: record.getFeatures().values()){
            if(isSuggestible != 0){
                result.add(user);
                break;}
        }
    }
    return result;
}




}
