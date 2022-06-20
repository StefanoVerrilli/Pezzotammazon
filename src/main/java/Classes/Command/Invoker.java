package Classes.Command;

import java.sql.SQLException;

public class Invoker {
    private Command currentCommand;
    public Invoker(Command command) {
        this.currentCommand = command;
    }

    public String executeOperation() throws SQLException {
        return this.currentCommand.Execute();
    }
}
