package Classes.Command;

import Classes.Product;
import Classes.ProductOperations;
import Classes.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class UserInfo implements Command{
    private Discriminator discriminator;

    public UserInfo(Discriminator discriminator){
        this.discriminator = discriminator;
    }
    @Override
    public String Execute() throws SQLException {
        return discriminator.UserDiscriminator();
    }
}
