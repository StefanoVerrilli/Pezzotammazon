package Classes.SuggestionSystemFacede;

import Classes.Clustering.Record;
import Classes.DataAnalysis.DatasetPreparation.DataSetElaboration;
import Classes.Order.IOrderDAO;
import Classes.OrderCollection.IOrderCollectionDAO;
import Classes.OrderCollection.OrderCollection;
import Classes.Product.ProductModel;
import Classes.User.IUserOperation;
import Classes.User.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataAnalysisFacade {

public List<ProductModel> getSuggestions(List<OrderCollection> orderCollectionList,
 UserModel User) throws SQLException{
    DataSetElaboration dataSetElaboration = new DataSetElaboration();
    Record record = dataSetElaboration.getData(User, orderCollectionList);
    String preferredCategory = dataSetElaboration.MaxPurchaseCategory(record);
    return dataSetElaboration.getSuggestions(preferredCategory, orderCollectionList);
 }

public Record getData(UserModel user, List<OrderCollection> orderCollectionList) throws SQLException{
    DataSetElaboration dataSetElaboration = new DataSetElaboration();
    return dataSetElaboration.getData(user,orderCollectionList);
}

public List<UserModel> getSuggestibleUsers(IUserOperation userOperation,
IOrderCollectionDAO orderCollectionDAO, IOrderDAO orderDAO) throws SQLException {
    List<UserModel> users = userOperation.getAll();
    List<UserModel> result = new ArrayList<>();
    for(UserModel user : users){
        List<OrderCollection> orderCollectionList = orderCollectionDAO.getAll(user.getId());
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
