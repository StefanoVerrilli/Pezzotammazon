package Classes.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFactory {

    public static Action getAction(HttpServletRequest request){
        String[] subPath = request.getRequestURI().split("/");
        System.out.println(subPath[subPath.length - 1]);
        return Actions.getAction(request.getMethod() + "/"+ subPath[subPath.length - 1 ]);
        //return Actions.getAction(request.getMethod() + request.getServletPath());
    }
}
