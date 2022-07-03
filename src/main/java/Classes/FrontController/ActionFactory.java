package Classes.FrontController;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public static Action getAction(HttpServletRequest request){
        String[] subPath = request.getRequestURI().split("/");
        return Actions.getAction(request.getMethod() + "/"+ subPath[subPath.length - 1 ]);
        //return Actions.getAction(request.getMethod() + request.getServletPath());
    }
}
