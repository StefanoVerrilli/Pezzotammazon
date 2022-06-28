package Classes.DAO;

import java.util.List;
import java.sql.SQLException;

public interface IGetAll<T> extends DAO{
    public List<T> getAll() throws SQLException;
}
