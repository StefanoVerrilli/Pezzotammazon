package Classes.Command;

import Classes.Models.User;

public class Dispatcher implements Command{

    private User user;
    private DiscriminatorInterface discriminator;

    public Dispatcher(DiscriminatorInterface discriminator, User user){
        this.user = user;
        this.discriminator = discriminator;
    }
    @Override
    public String Execute() {
        return user.getAccessType() == 1 ? this.discriminator.AdminPages() : this.discriminator.UserPages();
    }
}
