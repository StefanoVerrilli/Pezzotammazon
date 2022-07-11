package Classes.User.ChainofChecks;

import Classes.User.Hashing.ConcreteHashAlg;
import Classes.User.IUserOperation;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserValidationHandler extends Handler{
    @Override
    public String handle(HttpServletRequest request) throws Exception{
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        IUserOperation<UserModel> usersOperations = new UsersOperations(new ConcreteHashAlg());
        Optional<UserModel> user = usersOperations.CheckUser(mail, password);
        if(user.isPresent()){
            request.getSession().setAttribute("user",user.get());
            return next.handle(request);}
        else{
            request.getSession().setAttribute("error", "Username or Password are invalid");
            return "LogIn";
            }
    }
}
