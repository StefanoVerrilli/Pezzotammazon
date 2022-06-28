package Classes.DAO;

import java.sql.SQLException;

public interface IUpdateDAO<T> extends DAO{
    public void update(T t) throws SQLException;

}
