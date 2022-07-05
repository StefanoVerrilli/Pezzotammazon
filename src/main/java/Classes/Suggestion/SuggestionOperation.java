package Classes.Suggestion;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuggestionOperation implements ISuggestionDAO<SuggestionModel> {

    @Override
    public boolean add(SuggestionModel suggestion) throws SQLException {
        String query = "INSERT INTO Suggestion(user_id, product_id) "
            + "VALUES (?,?) ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,suggestion.getUser_id());
        p.setInt(2,suggestion.getProduct_id());
        p.executeUpdate();
        p.close();
        return true;
    }


    public List<SuggestionModel> getAll(Integer userId) throws SQLException {
        String query = "SELECT *"
        +   "FROM Suggestion "
        +   "WHERE User_id = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,userId);
        ResultSet rest = p.executeQuery();
        List<SuggestionModel> suggestionModelList = new ArrayList<>();
        while (rest.next()){
            SuggestionModel suggestion = new SuggestionModel(rest.getInt(1),rest.getInt(2));
            suggestionModelList.add(suggestion);
        }
        p.close();
        return suggestionModelList;
    }
    @Override
    public void delete(Integer userId,Integer productID)throws SQLException{
        String query = "DELETE FROM Suggestion "
            + "WHERE User_id =? AND Product_id =? ";
            PreparedStatement p = myDb.getConnection().prepareStatement(query);
            p.setInt(1,userId);
            p.setInt(2,productID);
            p.executeUpdate();
            p.close();
    }
}
