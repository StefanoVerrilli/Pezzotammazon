package Classes.DAO;

import Classes.User.UserModel;

import java.sql.SQLException;
import java.util.Optional;

public interface DAO {
    static final Database myDb = Database.getInstance();
}
