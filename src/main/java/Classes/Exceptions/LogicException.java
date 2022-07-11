package Classes.Exceptions;

import javax.servlet.http.HttpServletRequest;

public class LogicException extends RuntimeException{
    public LogicException(HttpServletRequest request,String message,String Container){
    request.getSession().setAttribute(Container,message);
    }
}
