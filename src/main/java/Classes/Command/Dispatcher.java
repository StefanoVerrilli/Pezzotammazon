package Classes.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    private final List<Command> operations = new ArrayList<>();

    public String executeOperation(Command command) throws SQLException {
        operations.add(command);
        return command.Execute();
    }
}
