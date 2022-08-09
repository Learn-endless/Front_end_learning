import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        //获取HttpSession对象中的属性
        String username = (String) session.getAttribute("username");
        Integer count = (Integer) session.getAttribute("count");
        //次数加1
        count+=1;
        //再次写入HttpSession对象
        session.setAttribute("count",count);
        //设置浏览器以 html 的格式来显示 body 中的内容,并且字符编码按照 utf8来
        resp.setContentType("text/html;charset=utf8");
        resp.getWriter().write("<h3> 欢迎您"+username+" 这是第"+count+"次访问"+"</h3>");
    }
}
