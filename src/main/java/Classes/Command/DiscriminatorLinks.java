package Classes.Command;

import Classes.ServletsRegulation.AdminState;
import Classes.ServletsRegulation.Context;
import Classes.ServletsRegulation.UserState;

public class DiscriminatorLinks implements DiscriminatorInterface{
    @Override
    public String UserPages() {
        Context context = new Context();
        UserState userState = new UserState(context);
        userState.LoadLink();
        return "/Homepage";
    }

    @Override
    public String AdminPages() {
        Context context = new Context();
        AdminState adminState = new AdminState(context);
        adminState.LoadLink();
        return "/Homepage";
    }
}
