package Classes.User.Access;

import Classes.Command.DiscriminatorLinks;
import Classes.Command.Dispatcher;
import Classes.Command.Invoker;
import Classes.ConcreteHashAlg;
import Classes.FrontController.Action;
import Classes.Navbar.BuildNavbar;
import Classes.User.ChainOfResponsability.*;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LogIn implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Handler h1 = new UserValidationHandler();
        Handler h2 = new CreateCartHandler();
        Handler h3 = new NavbarCreationHandler();
        Handler h4 = new RedirectHandler();
        h1.SetNext(h2);
        h2.SetNext(h3);
        h3.SetNext(h4);
        return h1.handle(request);
    }
}
