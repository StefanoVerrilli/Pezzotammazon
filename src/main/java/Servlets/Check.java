package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import Classes.UsersOperations;

@WebServlet(name = "Check", value = "/Check")
public class Check extends HttpServlet {
    private HttpSession oldsession;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
        try {
            if(UsersOperations.checkUser(mail,password)){
                oldsession = request.getSession(false);
                if(oldsession != null){
                    oldsession.invalidate();
                }
                HttpSession actualSession = request.getSession();
                String user = UsersOperations.GetUsername(mail);
                actualSession.setAttribute("user",user);
                actualSession.setAttribute("email",mail);
                int access_type = Integer.parseInt(UsersOperations.GetAccess(mail));
                actualSession.setAttribute("access_type",access_type);
                response.sendRedirect("Homepage.jsp");
            }
            else{
                response.sendRedirect("/LogIn.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {
        processRequest(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {
        processRequest(request,response);
    }
}
