package Classes.Command;

import Classes.Product;
import Classes.ProductOperations;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class AdminInfo implements Command{
    private Discriminator discriminator;

    public AdminInfo(Discriminator discriminator) {
        this.discriminator = discriminator;
    }

    @Override
    public String Execute() throws SQLException {
        return discriminator.AdminDiscriminator();
    }
}
