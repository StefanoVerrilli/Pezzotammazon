package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersOperations {

    private static final Database myDb = Database.getInstance();
    private static HashInterface MyHashfunction = new ConcreteHashAlg();


    public static boolean checkUser(String mail, String password) throws SQLException {

        String cc = null;
        String HashValue = MyHashfunction.HashValue(password);
        String query = "SELECT COUNT(*) "
                + "FROM users "
                + "WHERE email= ? AND password= ?";
        PreparedStatement p = myDb.getConnection().prepareStatement(query);
        p.setString(1,mail);
        p.setString(2,HashValue);
        ResultSet rest = p.executeQuery();
        while (rest.next()) {
            cc = rest.getString(1);
        }

        p.close();
        rest.close();

        if(Integer.parseInt(cc) == 1){
            return true;
        }else{
            return false;
        }
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
    public static boolean RegisterNewUser(String mail,String Password) throws SQLException{
        if(!checkMailExists(mail)){
            String Registration = "INSERT INTO users (email,password) "
                    + "VALUES(?,?)";
            PreparedStatement p = myDb.getConnection().prepareStatement(Registration);
            String MyHash = MyHashfunction.HashValue(Password);
            p.setString(1,mail);
            p.setString(2,MyHash);
            int result = p.executeUpdate();
            p.close();
            return true;
        }else{
            return false;
        }
    }
}
