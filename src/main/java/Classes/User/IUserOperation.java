package Classes.User;
import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetAll;
import Classes.DAO.IGetDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Definisce un'interfaccia per le classi che eseguono operazioni sull'utente
 * @param <T> Tipo generico
 */

public interface IUserOperation<T> extends DAO,IAddDAO<T>,IGetDAO<T>{

    /**
     * Verifica le credenziali dell'utente
     * @param email Email dell'utente
     * @param password Password dell'utente
     * @return Tipo generico, definito dall'implementazione, solitamente il modello che definisce l'utente
     * @throws SQLException Errore durante la query sul database
     */
    Optional<T> CheckUser(String email,String password) throws SQLException;

    /**
     * Ottiene tutti gli utenti
     * @return Lista con tipo generico, solitamente il modello che definisce l'utente
     * @throws SQLException Errore durante la query sul database
     */
    List<T> getAll() throws SQLException;
}
