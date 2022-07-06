package Classes.OrderCollection;

import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetAll;
import Classes.DAO.IGetDAO;

public interface IOrderCollectionDAO<T> extends DAO,IGetDAO<T>, IAddDAO<T>, IGetAll<T> {
}
