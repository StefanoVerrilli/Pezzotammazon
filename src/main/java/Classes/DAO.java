package Classes;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    static final Database myDb = Database.getInstance();
    Optional<T> get(int id) throws SQLException;
    boolean add(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(int id) throws SQLException;
}
