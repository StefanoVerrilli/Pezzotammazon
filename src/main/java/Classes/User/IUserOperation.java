package Classes.User;
import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetAll;
import Classes.DAO.IGetDAO;

import java.sql.SQLException;
import java.util.Optional;

public interface IUserOperation<T> extends DAO,IAddDAO<T>,IGetDAO<T>{
    Optional<UserModel> CheckUser(String email,String password) throws SQLException;

}
