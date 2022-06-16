package Classes.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFactory {

    public static Action getAction(HttpServletRequest request){
        return Actions.getAction(request.getMethod() + request.getServletPath());
    }
}
