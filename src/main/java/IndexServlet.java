/*Author: Chris Brown
* Date: 16/12/2015
* Description: Servlet for handling requests around pets*/

import Login.LoginServlet;
import Login.User;
import uk.ac.ncl.csc3422.kennelbooking.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    private static Kennel kennel = KennelFactory.initialiseKennel();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("kennel-report", "<p>" + getReport() + "</p>");

        User tempUser = LoginServlet.getUser();

        //If logged in, get the username and store it in a cookie
        if(tempUser != null){
            Cookie usr = new Cookie("user", tempUser.getUsername());
            usr.setMaxAge(60*60); //Cookie expires after 1 hour
            response.addCookie(usr);

            request.setAttribute("username",tempUser.getUsername());
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        }
        else {
            response.addHeader("message", "<p>You need to be logged in to use this service</p>");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dogName;

        if(request.getParameter("bookInDog") != null) {
            dogName = request.getParameter("bookInDog"); //get name
            if (dogName.isEmpty()) {
                response.addHeader("bk-name-error", "<p>Please enter the name of the dog you wish to book in</p>");
            }
            else {
                String dogSizeStr = request.getParameter("dog-size"); //get size
                DogSize dogSize;

                //Convert size to DogSize
                if (dogSizeStr.equals("small")) dogSize = DogSize.SMALL;
                else if (dogSizeStr.equals("medium")) dogSize = DogSize.MEDIUM;
                else dogSize = DogSize.GIANT;


                if (bookInDog(dogSize, dogName) != null) {
                    LoginServlet.addCurrentUserDog(dogName); //Add dog to user
                    response.addHeader("bk-msg", dogName + " has been booked into the kennel");
                } else {
                    response.addHeader("bk-msg", "Unfortunately their is not enough room in the kennel for " + dogName);
                }
            }
        }
        else
        {
            dogName = request.getParameter("checkoutDog");

            if(dogName.isEmpty()){
                response.addHeader("co-name-error", "<p>Please enter the name of the dog you wish to checkout</p>");
            }
            else {
                //can the user check the dog out?
                if(LoginServlet.getUser().ownsDog(dogName)){
                    checkoutDog(dogName); //remove from kennel
                    LoginServlet.removeCurrentUserDog(dogName); //Remove dog from user
                    response.addHeader("co-msg", dogName + " has been checked out of the kennel");
                }
                else {
                    //User doesn't own a dog by that name
                    response.addHeader("co-msg", "Our records show you don't have a dog by that name booked into the kennel");
                }
            }
        }

        response.setHeader("kennel-report", "<p>" + getReport() + "</p>");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
//        response.sendRedirect("/");

    }

    private static String getReport(){
        return KennelReport.generateReport(kennel);
    }

    private static Pen bookInDog(DogSize size, String dogName){
        return kennel.bookPen(size, dogName);
    }

    private static void checkoutDog(String dogName){
        kennel.checkout(dogName);
    }

}
