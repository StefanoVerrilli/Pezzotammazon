package Classes.Pattern;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Service", value = "*.do")
@MultipartConfig(maxFileSize = 16177215)
public class Service extends HttpServlet {

    protected void Service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(request);
            String View = action.execute(request,response);
            response.sendRedirect(View + ".jsp");
        } catch (Exception e) {
            throw new ServletException("Executing action failed",e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Service(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service(request,response);
    }
}
