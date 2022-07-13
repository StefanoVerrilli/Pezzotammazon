package Classes.Suggestion;

import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;

import java.sql.SQLException;
import java.util.List;

public interface ISuggestionOperations<T> extends IAddDAO<T> {

    void delete(Integer userID, Integer productID) throws SQLException;

    List<T> getAll(Integer userId) throws SQLException;

}
