package Classes.User;
import Classes.DAO.DAO;
import Classes.HashInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersOperations implements IUserOperation<UserModel> {

    private final HashInterface hashFunction;

    public UsersOperations(HashInterface hashFunction) {
        this.hashFunction = hashFunction;
    }

    @Override
    public Optional<UserModel> CheckUser(String mail, String password) throws SQLException {
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
    @Override
    public List<UserModel> getAll() throws SQLException {
        List<UserModel> result = new ArrayList<>();
        String query = "SELECT * "
                + "FROM users "
                + "WHERE User_type = 0 ";
        Statement stat = DAO.myDb.getConnection().createStatement();
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

    @Override
    public boolean add(UserModel user) throws SQLException {
        if(!checkMailExists(user.getEmail())){
            String Registration = "INSERT INTO users (email,password,username) "
                    + "VALUES(?,?,?)";
            PreparedStatement p = myDb.getConnection().prepareStatement(Registration);
            String MyHash = hashFunction.HashValue(user.getPassword());
            p.setString(1,user.getEmail());
            p.setString(2,MyHash);
            p.setString(3,user.getUsername());
            p.executeUpdate();
            p.close();
            return true;
        }
        return false;
    }

    @Override
    public Optional<UserModel> get(Integer Id) throws SQLException {
        String query = "SELECT * "
                    + "FROM users "
                    + "WHERE id = ? ";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setInt(1,Id);
        ResultSet rest = p.executeQuery();
         UserModel newUser = new UserModel.Builder(rest.getString(3))
                                        .setMail(rest.getString(1))
                                        .setPassword(rest.getString(2))
                                        .setID(rest.getInt(5))
                                        .setAccessType(rest.getInt(4))
                                        .build();
        return Optional.of(newUser);

    }
}
