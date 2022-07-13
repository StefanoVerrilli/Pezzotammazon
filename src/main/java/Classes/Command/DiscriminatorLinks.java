package Classes.Command;

import Classes.ServletsRegulation.AdminState;
import Classes.ServletsRegulation.Context;
import Classes.ServletsRegulation.State;
import Classes.ServletsRegulation.UserState;
import Classes.User.AccessLevels;

/**
 * Implementa {@link DiscriminatorInterface} e ritorna la pagina adatta a seconda del tipo di utente.
 * @see AccessLevels
 * @see UserState
 * @see Context
 */

public class DiscriminatorLinks implements DiscriminatorInterface{

    @Override
    public String UserPages() {
        UserState userState = new UserState();
        Context context = new Context(userState);
        context.LoadLinks();
        return "/UserPages/UserProducts";
    }

    @Override
    public String AdminPages() {
        State AdminState = new AdminState();
        Context context = new Context(AdminState);
        context.LoadLinks();
        return "/AdminPages/ProductsTable";
    }
}
