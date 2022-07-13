package Classes.Order;

import Classes.DAO.DAO;
import Classes.DAO.Database;

import java.sql.SQLException;
import java.util.List;

/**
 * Definisce le operazioni da eseguire sugli ordini.
 * @param <T> Tipo generico per l'output.
 */

public interface IOrderOperations<T>{

    /**
     * Ottiene tutti gli oggetti ordinati per categoria.
     * @param Category Categoria dalla quale ottenere gli oggetti.
     * @param collectionID Identificativo degli ordini in cui cercare.
     * @return Oggetti ordinati per la categoria inserita per l'identificativo inserito.
     * @throws SQLException Errore durante la ricerca nel database.
     */
    List<T> getAllByCategory(String Category, Integer collectionID) throws SQLException;

    /**
     * Ottiene tutti gli ordini in base all'ID dell'ordinazione.
     * @param collectionID Identificativo numerico dell'ordinazione.
     * @return Lista di prodotti ordinati.
     * @throws SQLException Errore durante la ricerca nel database.
     */
    List<T> getAll(Integer collectionID) throws SQLException;
}
