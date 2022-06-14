package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersOperations {

    private static final Database myDb = Database.getInstance();
    private static HashInterface MyHashfunction = new ConcreteHashAlg();


    public static User checkUser(String mail, String password) throws SQLException {

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
            return null;
        }
        User newUser = new User();
        newUser.setEmail(rest.getString(1));
        newUser.setPassword(rest.getString(2));
        newUser.setUsername(rest.getString(3));
        newUser.setAccessType(rest.getInt(4));

        p.close();
        rest.close();

        return newUser;
    }

    public static boolean checkMailExists(String mail) throws SQLException{
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
    public static boolean RegisterNewUser(String mail,String Password,String username) throws SQLException{
        if(!checkMailExists(mail)){
            String Registration = "INSERT INTO users (email,password,username) "
                    + "VALUES(?,?,?)";
            PreparedStatement p = myDb.getConnection().prepareStatement(Registration);
            String MyHash = MyHashfunction.HashValue(Password);
            p.setString(1,mail);
            p.setString(2,MyHash);
            p.setString(3,username);
            int result = p.executeUpdate();
            p.close();
            return true;
        }else{
            return false;
        }
    }
    public static String GetUsername(String email) throws SQLException{
        String cc = null;
        if(checkMailExists(email)) {
            String UsernameQuery = "SELECT username "
                    + "FROM users "
                    + "WHERE email=?";
            PreparedStatement p = myDb.getConnection().prepareStatement(UsernameQuery);
            p.setString(1, email);
            ResultSet rest = p.executeQuery();
            while (rest.next()) {
                cc = rest.getString(1);
            }
            p.close();
            rest.close();
        }
            return cc;
        }

    public static String GetAccess(String email) throws SQLException{
        String cc = null;
        String UsernameQuery = "SELECT User_type "
                + "FROM users "
                + "WHERE email=?";
        PreparedStatement p = myDb.getConnection().prepareStatement(UsernameQuery);
        p.setString(1,email);
        ResultSet rest = p.executeQuery();
        while (rest.next()) {
            cc = rest.getString(1);
        }
        p.close();
        rest.close();
        return cc;

    }
}
