package Classes.Command;

import Classes.User.AccessLevels;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import java.util.Optional;

public class Dispatcher implements Command{

    private UserModel user;
    private DiscriminatorInterface discriminator;

    public Dispatcher(DiscriminatorInterface discriminator, UserModel user){
        this.user = user;
        this.discriminator = discriminator;
    }
    @Override
    public String Execute() {
        return user.getAccessType() == AccessLevels.Admin ? this.discriminator.AdminPages() : this.discriminator.UserPages();
    }
}
