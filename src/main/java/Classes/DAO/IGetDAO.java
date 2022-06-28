package Classes.DAO;

import java.sql.SQLException;
import java.util.Optional;

public interface IGetDAO<T> extends DAO {
    public Optional<T> get(Integer Id) throws SQLException;
}
