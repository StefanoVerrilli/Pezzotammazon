package Classes.Command;

import Classes.User.AccessLevels;
import Classes.User.UserModel;

public class Dispatcher implements Command{

    private final UserModel user;
    private final DiscriminatorInterface discriminator;

    public Dispatcher(DiscriminatorInterface discriminator, UserModel user){
        this.user = user;
        this.discriminator = discriminator;
    }
    @Override
    public String Execute() {
        return user.getAccessType() == AccessLevels.Admin ? this.discriminator.AdminPages() : this.discriminator.UserPages();
    }
}
