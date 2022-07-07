package Classes.DAO;

import java.sql.SQLException;

public interface IUpdateDAO<T> {
    public void update(T t) throws SQLException;

}
