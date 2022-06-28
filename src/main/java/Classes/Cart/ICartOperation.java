package Classes.Cart;

import java.sql.SQLException;
import java.util.Optional;

public interface ICartOperation {
    public Optional<CartModel> getCart() throws SQLException;
    public void EmptyCart() throws SQLException;
}
