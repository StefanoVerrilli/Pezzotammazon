package Classes.User;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetAll;
import Classes.DAO.IGetDAO;

import java.sql.SQLException;
import java.util.Optional;

public interface IUserOperation extends IAddDAO<UserModel>, IGetDAO<UserModel>, IGetAll<UserModel> {
    Optional<UserModel> CheckUser(String email,String password) throws SQLException;

}
