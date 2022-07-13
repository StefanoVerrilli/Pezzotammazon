package Classes.User.Access;

import Classes.FrontController.Action;
import Classes.User.ChainofChecks.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Esegue le operazioni di login. Utilizza una Chain of Responsibility per richiamare le azioni necessarie
 * come {@link UserValidationHandler}, {@link CreateCartHandler}, {@link NavbarCreationHandler}, {@link LoadDataHandler} e
 * {@link RedirectHandler}.
 */

public class LogIn implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Handler h1 = new UserValidationHandler();
        Handler h2 = new CreateCartHandler();
        Handler h3 = new NavbarCreationHandler();
        Handler h4 = new LoadDataHandler();
        Handler h5 = new RedirectHandler();
        h1.SetNext(h2);
        h2.SetNext(h3);
        h3.SetNext(h4);
        h4.SetNext(h5);
        return h1.handle(request);
    }
}
