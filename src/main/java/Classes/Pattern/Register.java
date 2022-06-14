package Classes.Pattern;

import Classes.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        if(UsersOperations.RegisterNewUser(mail,password,username)){
            return "LogIn";
        }else{
            request.getSession().setAttribute("error","Mail already exists");
            return "register";
        }
    }
}
