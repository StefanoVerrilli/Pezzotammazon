package Classes.Command;

import Classes.User.AccessLevels;
import Classes.User.UserModel;

/**
 * Implementa i metodi di {@link  Command}.  Seleziona l'esecuzione a seconda del tipo di utente.
 * @see UserModel
 * @see AccessLevels
 * @see DiscriminatorInterface
 */

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
