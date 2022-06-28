package Classes.Command;

import Classes.ServletRegulation.AdminState;
import Classes.ServletRegulation.Context;
import Classes.ServletRegulation.UserState;

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
