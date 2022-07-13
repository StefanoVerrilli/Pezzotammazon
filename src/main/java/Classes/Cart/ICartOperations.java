package Classes.Cart;

import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Definisce l'interfaccia per le classi concrete che definiscono le operazioni sul database per il carrello.
 * @param <T> Tipologia di dato di ingresso per l'aggiunta di dati.
 * @param <G> Tipologia di dato di ingresso per l'ottenimento dei dati.
 * @see DAO
 * @see IAddDAO
 * @see IGetDAO
 */
public interface ICartOperations<T,G> extends IAddDAO<T>, IGetDAO<T> {
    void EmptyCart(T cart) throws SQLException;

    List<G> getAll(Integer UserID) throws SQLException;

    void delete(Integer cartId, Integer productId) throws SQLException;


}
