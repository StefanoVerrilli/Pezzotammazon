package Classes.DAO;

import java.util.List;
import java.sql.SQLException;

public interface IGetAll<T>{
    public List<T> getAll(Integer id) throws SQLException;
}
