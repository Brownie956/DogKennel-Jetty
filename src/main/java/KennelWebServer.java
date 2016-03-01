/*Author: Chris Brown
* Date: 15/12/2015
* Description: Main class for Jetty Server*/

import Login.LoginServlet;
import Login.LogoutServlet;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.ArrayList;
import java.util.List;

public class KennelWebServer {

    public static void main(String[] args){
        Server server = new Server(80);

        try {
            WebAppContext context = new WebAppContext();
            context.setContextPath("/");
            context.setResourceBase(KennelWebServer.class.getResource("/webapp/").toURI().toASCIIString());
            final ContainerInitializer initializer = new ContainerInitializer(new JettyJasperInitializer(), null);
            List<ContainerInitializer> initializers = new ArrayList<ContainerInitializer>(){{add(initializer);}};
            context.setAttribute("org.eclipse.jetty.containerInitializers", initializers);
            context.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
            context.addBean(new ServletContainerInitializersStarter(context), true);


            ServletHolder holderDefault = new ServletHolder("default", DefaultServlet.class);
            ServletHolder indexDefault = new ServletHolder("index", IndexServlet.class);
            ServletHolder loginDefault = new ServletHolder("login", LoginServlet.class);
            ServletHolder logoutDefault = new ServletHolder("logout", LogoutServlet.class);

            context.addServlet(indexDefault, "/");
            context.addServlet(indexDefault, "/index");
            context.addServlet(loginDefault, "/login");
            context.addServlet(logoutDefault, "/logout");

            server.setHandler(context);


            server.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
