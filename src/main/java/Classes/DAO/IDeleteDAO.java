package Classes.DAO;

import java.sql.SQLException;

public interface IDeleteDAO extends DAO {
    public void delete(Integer ID) throws SQLException;
}
