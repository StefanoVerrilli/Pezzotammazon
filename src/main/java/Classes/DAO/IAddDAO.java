package Classes.DAO;

import java.sql.SQLException;

public interface IAddDAO<T> extends DAO{

    public boolean add(T t) throws SQLException;
}
