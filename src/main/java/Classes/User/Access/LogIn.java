package Classes.User.Access;

import Classes.Command.DiscriminatorLinks;
import Classes.Command.Dispatcher;
import Classes.Command.Invoker;
import Classes.Pattern.Action;
import Classes.Pattern.BuildNavbar;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LogIn implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        UsersOperations usersOperations = new UsersOperations();
        Optional<UserModel> user = usersOperations.CheckUser(mail,password);
        if(user.isPresent()){
            request.getSession().setAttribute("user",user.get());
            BuildNavbar.GetNavbar(request);
            DiscriminatorLinks discriminatorLinks = new DiscriminatorLinks();
            Invoker invoker = new Invoker(new Dispatcher(discriminatorLinks,user.get()));
            return invoker.executeOperation();
        }else{
            request.setAttribute("error","Username or Password are invalid");
            return "LogIn";
        }
    }
}
