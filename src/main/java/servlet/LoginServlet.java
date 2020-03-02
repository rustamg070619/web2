package servlet;

import model.User;
import service.UserService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("email", "");
        pageVariables.put("password", "");
        response.getWriter().println(PageGenerator.instance().getPage("authPage.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserService.getInstance().authUser(new User(email, password));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("authPage.html", pageVariables));

    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("email", request.getParameter("email"));
        pageVariables.put("password", request.getParameter("password"));
        return pageVariables;
    }

}
