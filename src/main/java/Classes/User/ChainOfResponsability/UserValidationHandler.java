package Classes.User.ChainOfResponsability;

import Classes.ConcreteHashAlg;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserValidationHandler extends Handler{
    @Override
    public String handle(HttpServletRequest request) throws Exception{
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        UsersOperations usersOperations = new UsersOperations(new ConcreteHashAlg());
        Optional<UserModel> user = usersOperations.CheckUser(mail, password);
        if(user.isPresent()){
            request.getSession().setAttribute("user",user.get());
            return next.handle(request);}
        else{
            request.setAttribute("error", "Username or Password are invalid");
            return "LogIn";
            }
    }
}
