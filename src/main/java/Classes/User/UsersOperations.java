package Classes.User;
import Classes.DAO.Database;
import Classes.User.Hashing.HashInterface;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe contenente le operazioni sull'utente
 */

public class UsersOperations implements IUserOperation<UserModel> {

    private final HashInterface hashFunction;

    private final Database myDb = Database.getInstance();

    /**
     * Aggiunge l'implementazione dell'algoritmo di hashing da utilizzare
     * @param hashFunction Tipo di algoritmo da utilizzare
     * @see HashInterface Interfaccia generica che le implementazioni devono rispettare
     * @see HashInterface Implementazione dell'algoritmo MD5
     */
    public UsersOperations(HashInterface hashFunction) {
        this.hashFunction = hashFunction;
    }

    /**
     * Verifica le credenziali di accesso dell'utente
     * @param mail Email dell'utente
     * @param password Password dell'utente
     * @return Modello contenente le informazioni dell'utente
     * @throws SQLException Errore durante la query sul database
     */
    @Override
    public Optional<UserModel> CheckUser(String mail, String password) throws SQLException, NoSuchAlgorithmException {
        String HashValue = hashFunction.HashValue(password);
        String query = "SELECT * "
                + "FROM users "
                + "WHERE email= ? AND password= ?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,mail);
        p.setString(2,HashValue);
        ResultSet rest = p.executeQuery();
        if(!rest.next()){
            return Optional.empty();
        }
        UserModel newUser = new UserModel.Builder(rest.getString(3))
                .setMail(rest.getString(1))
                .setPassword(rest.getString(2))
                .setID(rest.getInt(5))
                .setAccessType(rest.getInt(4))
                .build();
        p.close();
        rest.close();

        return Optional.of(newUser);
    }

    /**
     * Controlla se l'email è già presente sul database degli utenti
     * @param mail Email che si vuole cercare
     * @return Valore booleano true se già presente, false altrimenti
     * @throws SQLException Errore durante la query sul database
     */
    private boolean checkMailExists(String mail) throws SQLException{
        String query = "SELECT COUNT(*) "
                + "FROM users "
                + "WHERE email= ?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,mail);
        ResultSet rest = p.executeQuery();
        int cc = Integer.parseInt(rest.getString(1));
        p.close();
        rest.close();
        return cc != 0;
    }

    /**
     * Ottiene la lista completa di utenti presenti sul database
     * @return Lista di utenti presenti sul database
     * @throws SQLException Errore durante la query sul database
     * @see UserModel
     * @see Classes.User.UserModel.Builder
     */
    @Override
    public List<UserModel> getAll() throws SQLException {
        List<UserModel> result = new ArrayList<>();
        String query = "SELECT * "
                + "FROM users "
                + "WHERE User_type = 0 ";
        Statement stat = myDb.getConnection().createStatement();
        ResultSet rest = stat.executeQuery(query);
        while(rest.next()){
                UserModel currentUser = new UserModel.Builder(rest.getString("username"))
                        .setMail(rest.getString("email"))
                        .setID(rest.getInt("id"))
                        .setPassword(rest.getString("password"))
                        .setAccessType(rest.getInt("User_type"))
                        .build();
                result.add(currentUser);
        }
        return result;
    }

    /**
     * Aggiunge un utente sul database
     * @param user Utente che si vuole aggiungere, completo di tutti i dettagli
     * @return Valore booleano true se l'operazione avviene con successo, false altrimenti
     * @throws SQLException Errore durante la query sul database
     * @see UserModel
     */
    @Override
    public boolean add(UserModel user) throws SQLException {
        if(!checkMailExists(user.getEmail())){
            String Registration = "INSERT INTO users (email,password,username) "
                    + "VALUES(?,?,?)";
            PreparedStatement p = myDb.getConnection().prepareStatement(Registration);
            String MyHash;
            try {
                MyHash = hashFunction.HashValue(user.getPassword());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            p.setString(1,user.getEmail());
            p.setString(2,MyHash);
            p.setString(3,user.getUsername());
            p.executeUpdate();
            p.close();
            return true;
        }
        return false;
    }

    /**
     * Ottiene l'utente il cui ID viene passato come parametro della funzione
     * @param Id Identificativo univoco dell'utente sul database
     * @return Modello completo dell'utente, contenenti tutte le informazioni presenti sul database
     * @throws SQLException Errore durante la query sul database
     * @see UserModel
     * @see Classes.User.UserModel.Builder
     */
    @Override
    public Optional<UserModel> get(Integer Id) throws SQLException {
        String query = "SELECT * "
                    + "FROM users "
                    + "WHERE id = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Id);
        ResultSet rest = p.executeQuery();
        if(rest.isClosed())
            return Optional.empty();
        UserModel newUser = new UserModel.Builder(rest.getString(3))
                                        .setMail(rest.getString(1))
                                        .setPassword(rest.getString(2))
                                        .setID(rest.getInt(5))
                                        .setAccessType(rest.getInt(4))
                                        .build();
        return Optional.of(newUser);

    }
}
