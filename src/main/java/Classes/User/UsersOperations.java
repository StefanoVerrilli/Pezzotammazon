package Classes.User;
import Classes.ConcreteHashAlg;
import Classes.DAO.DAO;
import Classes.HashInterface;
import Classes.User.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersOperations implements IUserOperation {

    private static HashInterface MyHashfunction = new ConcreteHashAlg();

    public Optional<UserModel> CheckUser(String mail, String password) throws SQLException {
        String cc = null;
        String HashValue = MyHashfunction.HashValue(password);
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
        String cc = null;
        String query = "SELECT COUNT(*) "
                + "FROM users "
                + "WHERE email= ?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,mail);
        ResultSet rest = p.executeQuery();
        while (rest.next()) {
            cc = rest.getString(1);
        }
        p.close();
        rest.close();
        if(Integer.parseInt(cc) != 0){
            return true;
        }else{
            return false;
        }

    }

    public List<UserModel> getAll() throws SQLException {
        List result = new ArrayList();
        String query = "SELECT * "
                + "FROM users ";
        Statement stat = (Statement) DAO.myDb.getConnection().createStatement();
        ResultSet rest = stat.executeQuery(query);
        while(rest.next()){
            if(rest.getInt("User_type") != 1) {
                UserModel currentUser = new UserModel.Builder(rest.getString("username"))
                        .setMail(rest.getString("email"))
                        .setID(rest.getInt("id"))
                        .setPassword(rest.getString("password"))
                        .setAccessType(rest.getInt("User_type"))
                        .build();
                result.add(currentUser);
            }
        }
        return result;
    }

    @Override
    public boolean add(UserModel user) throws SQLException {
        if(!checkMailExists(user.getEmail())){
            String Registration = "INSERT INTO users (email,password,username) "
                    + "VALUES(?,?,?)";
            PreparedStatement p = myDb.getConnection().prepareStatement(Registration);
            String MyHash = MyHashfunction.HashValue(user.getPassword());
            p.setString(1,user.getEmail());
            p.setString(2,MyHash);
            p.setString(3,user.getUsername());
            p.executeUpdate();
            p.close();
            return true;
        }
        return false;
    }
}
