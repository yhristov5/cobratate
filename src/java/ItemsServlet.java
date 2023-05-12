import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/ItemsServlet"})
public class ItemsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = null;
        String merc1 = request.getParameter("merc1");
        String bmw = request.getParameter("bmw");
        String merc2 = request.getParameter("merc2");
        String tempcar = null;
        
        Cookie car = new Cookie("car", tempcar);
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies) {
            if (c.getName().equals("username")) {
                user = c.getValue();
            }
        }
        String webpage = """
                        <!DOCTYPE html>
                        <html>
                           <head>
                           </head>
                           <body>
                              Zdravei %s
                              <br>
                              izbral si %s
                           </body>
                        <html> 
                        """.formatted(user, car.getValue());
        
        response.getWriter().println(webpage);
        }
}
