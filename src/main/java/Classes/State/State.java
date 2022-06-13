package Classes.State;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface State {
    void doAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
