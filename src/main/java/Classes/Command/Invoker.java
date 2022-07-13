package Classes.Command;

import java.sql.SQLException;

/**
 * Invoca il comando {@link Command}.
 */

public class Invoker {
    private final Command currentCommand;
    public Invoker(Command command) {
        this.currentCommand = command;
    }

    public String executeOperation() throws SQLException {
        return this.currentCommand.Execute();
    }
}
