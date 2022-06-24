package Classes.DAO;

import java.sql.SQLException;
import java.util.Optional;

public interface DAO<T> {
    static final Database myDb = Database.getInstance();
    Optional<T> get(Integer id) throws SQLException;
    boolean add(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(Integer id) throws SQLException;
}
