package Classes.User;
import Classes.DAO.IAddDAO;

import java.sql.SQLException;
import java.util.Optional;

public interface IUserOperation extends IAddDAO<UserModel> {
    Optional<UserModel> CheckUser(String email,String password) throws SQLException;

}
