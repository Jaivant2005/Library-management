import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserLogin")
public class AdminLoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(password);
        // Basic validation
        if (userEmail == null || password == null || userEmail.isEmpty() || password.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email or Password cannot be empty.");
            return;
        }

        try {
            User user = userDAO.getUser(userEmail);
            System.out.println(user.getPassword());
            if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
                // Redirect to the student dashboard
                response.sendRedirect("Adminportal.html");
            } else {
                // Redirect back to login with an error message
                response.sendRedirect("Adminlogin.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

}
