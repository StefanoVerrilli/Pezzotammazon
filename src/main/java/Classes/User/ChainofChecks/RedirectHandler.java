package Classes.User.ChainofChecks;

import Classes.Command.DiscriminatorLinks;
import Classes.Command.Dispatcher;
import Classes.Command.Invoker;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Esegue il redirect alla pagina principale a seconda dell'utente.
 * @see DiscriminatorLinks
 * @see Invoker
 * @see Dispatcher
 */

public class RedirectHandler extends Handler{

    @Override
    public String handle(HttpServletRequest request) throws Exception {
        DiscriminatorLinks discriminatorLinks = new DiscriminatorLinks();
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        Invoker invoker = new Invoker(new Dispatcher(discriminatorLinks, user));
        return invoker.executeOperation();
    }
}
