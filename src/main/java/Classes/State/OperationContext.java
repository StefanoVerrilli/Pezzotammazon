package Classes.State;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OperationContext extends HttpServlet implements State{
    private State OperationState;

    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public State getOperationState() {
        return this.OperationState;
    }

    public void setOperationState(State operationState) {
        this.OperationState = operationState;
    }
}
