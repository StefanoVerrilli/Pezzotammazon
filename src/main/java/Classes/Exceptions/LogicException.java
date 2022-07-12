package Classes.Exceptions;

import javax.servlet.http.HttpServletRequest;

public class LogicException extends RuntimeException{
    public LogicException(HttpServletRequest request,String Container,String message){
    request.getSession().setAttribute(Container,message);
    }
}
