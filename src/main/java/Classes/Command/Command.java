package Classes.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface Command {

    abstract String Execute() throws SQLException;
}
