package Classes.Command;

import Classes.State.AdminState;
import Classes.State.Context;
import Classes.State.UserState;

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
