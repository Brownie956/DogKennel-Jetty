/*Author: Chris Brown
* Date: 16/12/2015
* Description: Servlet to handle logging out*/
package Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Logout user
        LoginServlet.logoutUser();

        //Invalidate session
        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();

        response.addHeader("message", "You have been successfully logged out");
        request.getRequestDispatcher("/login").forward(request,response);
    }
}
