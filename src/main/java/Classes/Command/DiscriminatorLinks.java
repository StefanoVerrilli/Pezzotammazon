package Classes.Command;

import Classes.ServletsRegulation.AdminState;
import Classes.ServletsRegulation.Context;
import Classes.ServletsRegulation.State;
import Classes.ServletsRegulation.UserState;

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
