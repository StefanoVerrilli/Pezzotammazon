package Classes.DAO;

import java.sql.SQLException;

public interface IAddDAO<T>{

    public boolean add(T t) throws SQLException;
}
