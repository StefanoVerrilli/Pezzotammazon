package Classes.Pattern;

import Classes.Command.DiscriminatorLinks;
import Classes.Command.Dispatcher;
import Classes.Command.Invoker;
import Classes.Models.User;
import Classes.DAO.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        User user = UsersOperations.checkUser(mail,password);
        if(user != null){
            request.getSession().setAttribute("access_type",user.getAccessType());
            request.getSession().setAttribute("user",user);
            BuildNavbar.GetNavbar(request);
            DiscriminatorLinks discriminatorLinks = new DiscriminatorLinks();
            Invoker invoker = new Invoker(new Dispatcher(discriminatorLinks,user));
            return invoker.executeOperation();
        }else{
            request.setAttribute("error","Failed Login");
            return "LogIn";
        }
    }
}
