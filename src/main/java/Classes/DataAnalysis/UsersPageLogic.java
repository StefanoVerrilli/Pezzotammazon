package Classes.DataAnalysis;

import Classes.FrontController.Action;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersPageLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsersOperations usersOperations  = new UsersOperations();
        List<UserModel> userModelList = usersOperations.getAll();
        request.getSession().setAttribute("UsersList",userModelList);
        return "/AdminPages/UsersPage";
    }
}
