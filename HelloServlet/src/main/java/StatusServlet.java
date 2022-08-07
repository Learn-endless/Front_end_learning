import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/status")
public class StatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //状态码只是告知浏览器当前是个什么状态,并不影响浏览器显示body数据
        resp.setStatus(404);
        resp.getWriter().write("hello word");
    }
}
