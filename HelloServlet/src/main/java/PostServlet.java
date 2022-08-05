import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//这里必须加上 / ,硬性规定
@WebServlet("/postHello")
public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        解决 resp乱码问题。
//        从两个方面来考虑：
//        1.生成（取决于IDEA当前是以啥样的编码来组织我们的项目的,UTF8）
//        2.展示（浏览器是以什么编码的方式来解析响应的,默认跟随系统,GBK）
        resp.setContentType("text/html;charset=utf8");
        resp.getWriter().write("你好 java...");

        // 1 i l I L
    }
}
