package Classes.Command;
import java.sql.SQLException;

/**
 * Interfaccia per i Command utilizzati in {@link Invoker}.
 */

public interface Command {

    String Execute() throws SQLException;
}
