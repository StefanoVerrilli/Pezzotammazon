package Classes.Pattern;

import Classes.Models.User;
import Classes.DAO.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userToAdd = new User();
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
