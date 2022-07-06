package Classes.SuggestionSystemFacede;

import Classes.Clustering.Record;
import Classes.DataAnalysis.DatasetPreparation.DataSetElaboration;
import Classes.OrderCollection.OrderCollection;
import Classes.Product.ProductModel;
import Classes.User.UserModel;

import java.sql.SQLException;
import java.util.List;

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




}
