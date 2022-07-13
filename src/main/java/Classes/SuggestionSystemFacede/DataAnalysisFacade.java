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

/**
 * Facade per l'analisi dei dati degli utenti.
 */

public class DataAnalysisFacade {

    /**
     * Permette di ottenere i suggerimenti dei prodotti per un dato utente.
     * @param orderCollectionList Lista di oggetti {@link OrderCollectionModel} contenenti gli ordini.
     * @param User Oggetto {@link UserModel} contenente i dati dell'utente.
     * @return Lista di oggetti {@link ProductModel} contenente i prodotti suggeriti per l'utente.
     * @throws SQLException
     */
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

    /**
     * Permette di ottenere gli utenti che hanno effettuato almeno un ordine.
     * @param userOperation Operazioni sugli utenti.
     * @param orderCollection Operazioni sugli ordini.
     * @return Lista di oggetti {@link UserModel} contenente gli utenti che hanno effettuato almeno un ordine.
     * @throws SQLException
     */
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
