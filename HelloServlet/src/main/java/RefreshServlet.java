import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/refresh")
public class RefreshServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Refresh","1");
        resp.setContentType("text/html;charset=utf8");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = format.format(System.currentTimeMillis());
        System.out.println(format1);
        resp.getWriter().write("time : "+format1);
    }
}
