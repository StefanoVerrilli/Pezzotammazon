package Classes.Pattern;

import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserModel userToAdd = new UserModel();
        userToAdd.setEmail(request.getParameter("mail"));
        userToAdd.setPassword(request.getParameter("password"));
        userToAdd.setUsername(request.getParameter("username"));
        UsersOperations usersOperations = new UsersOperations();
        if(usersOperations.add(userToAdd)){
            return "LogIn";
        }else{
            request.getSession().setAttribute("error","Mail already exists");
            return "register";
        }
    }
}
