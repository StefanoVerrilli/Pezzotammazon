package Classes.User.Access;

import Classes.FrontController.Action;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserModel userToAdd = new UserModel.Builder(request.getParameter("username"))
                .setMail(request.getParameter("mail"))
                .setPassword(request.getParameter("password"))
                .build();
        UsersOperations usersOperations = new UsersOperations();
        if(usersOperations.add(userToAdd)){
            return "LogIn";
        }else{
            request.getSession().setAttribute("error","Mail already exists");
            return "Register";
        }
    }
}
