import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设定状态码
        resp.setStatus(302);
        //设置响应头 Location
        resp.setHeader("Location","https://www.sogou.com");

        //更简单的写法
        //resp.sendRedirect("https://www.sogou.com");
    }
}
