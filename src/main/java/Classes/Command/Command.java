package Classes.Command;
import java.sql.SQLException;

public interface Command {

    String Execute() throws SQLException;
}
