package taxi.controller.driver;

import taxi.lib.Injector;
import taxi.model.Driver;
import taxi.service.AuthenticationService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final AuthenticationService authentication =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/drivers/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Driver driver = authentication.login(login, password);
        HttpSession session = req.getSession();
        session.setAttribute("driver_id", driver.getId());
        resp.sendRedirect(req.getContextPath() + "/drivers/add");
    }
}
