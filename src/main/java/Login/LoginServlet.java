/* Author: Chris Brown
* Date: 15/12/2015
* Description: Servlet to handle logging in*/
package Login;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginServlet extends HttpServlet {

    private static User currentUser;

    private static List<User> users = new ArrayList<User>();

    public LoginServlet(){
        //Test users
        User p1 = new User("Chris", "password1");
        User p2 = new User("Tom", "password2");
        User p3 = new User("Jess", "password3");
        User p4 = new User("Alex", "password4");
        users.add(p1);
        users.add(p2);
        users.add(p3);
        users.add(p4);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(currentUser != null) response.sendRedirect("/");
        else if(request.getRequestURI().equals("/login")){
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else {
            response.addHeader("message", "<p>You need to be logged in to use this service</p><br/>");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        User tempUser = new User(user, pass);

        if(user.isEmpty() || pass.isEmpty()) { //basic validation
            if (user.isEmpty()) { //Is username field empty?
                response.addHeader("user-error", "<p>Please enter a username</p>");
            }

            if (pass.isEmpty()) { //Is password field empty?
                response.addHeader("pass-error", "<p>Please enter a password</p>");
            }
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

        else if(!searchUser(tempUser)){ //Are they a valid user?
            response.addHeader("message", "<p>Sorry we can't find that user and password combination in our records</p>");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else { //Valid user, log them in
            currentUser = tempUser;
            updateCurrentUser();
            Cookie usr = new Cookie("user", currentUser.getUsername());
            usr.setMaxAge(60 * 60); //Cookie expires after 1 hour
            response.addCookie(usr);

            response.sendRedirect("/");
        }

    }

    public static User getUser(){
        return currentUser;
    }

    public static void logoutUser(){
        currentUser = null;
    }

    private boolean searchUser(User user){
        for(User usr: users){
            if(user.equals(usr)) return true;
        }
        return false;
    }

    private static int getCurrentUserIndex(){
        for(int i = 0; i<users.size(); i++){
            if(currentUser.equals(users.get(i))) return i;
        }
        return -1;
    }

    private static void updateCurrentUser(){
        currentUser = users.get(getCurrentUserIndex());
    }

    public static void addCurrentUserDog(String dogName){
        int currentUserIndex = getCurrentUserIndex();
        users.get(currentUserIndex).addDog(dogName);
        updateCurrentUser();
    }

    public static void removeCurrentUserDog(String dogName){
        int currentUserIndex = getCurrentUserIndex();
        users.get(currentUserIndex).removeDog(dogName);
        updateCurrentUser();
    }
}
