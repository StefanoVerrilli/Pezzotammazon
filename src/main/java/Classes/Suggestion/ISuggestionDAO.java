package Classes.Suggestion;

import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;

import java.sql.SQLException;

public interface ISuggestionDAO<T> extends DAO,IAddDAO<T> {

    void delete(Integer userID, Integer productID) throws SQLException;

}
