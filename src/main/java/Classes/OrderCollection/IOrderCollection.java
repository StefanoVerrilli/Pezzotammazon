package Classes.OrderCollection;

import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetAll;
import Classes.DAO.IGetDAO;

import java.sql.SQLException;

public interface IOrderCollection<T> extends DAO,IGetDAO<T>, IAddDAO<T>, IGetAll<T> {

    boolean AddSingleOrders(int User_id) throws SQLException;
}
