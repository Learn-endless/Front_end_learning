import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if("zhangsan".equals(username) && "123".equals(password)){
            //登录成功
            HttpSession session = req.getSession(true);    //参数true表示没有就创建一个 HttpSession对象
            session.setAttribute("username",username);     //将用户名存储进去（通过键值对来设置成属性）
            session.setAttribute("count",0);            //将该客户第几次访问的数据也存进去
            //这里首先设置响应的状态码为 302 ,同时将 响应头 里的 Location 字段设置为 index。
            resp.sendRedirect("index");
        }else{
            //登录失败
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("<h3>login fail!</h3>");
        }
    }
}
