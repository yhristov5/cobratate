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

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        
    try {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cars");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT COUNT(*) FROM potrebiteli WHERE name=\"%s\"", user));
        rs.next();
        ResultSet rs1 = stmt.executeQuery(String.format( "SELECT pass FROM potrebiteli WHERE name=\"%s\"", user));
        rs1.next();
        ResultSet rs2 = stmt.executeQuery(String.format("SELECT mail FROM potrebiteli WHERE name=\"%s\"", user));
        rs2.next();
        
        if(rs.getString(1).equals("1")) {
            if(rs1.getString(1).equals(pass)) {
                if(rs2.getString(1).equals(email)) {
                response.getWriter().println("Login successful");
                Cookie c = new Cookie("username", user);
                response.addCookie(c);
                response.sendRedirect("index.html");
                } else response.getWriter().println("Login failed");
            } else response.getWriter().println("Login failed");
        } else response.getWriter().println("Login failed");
        
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
}
