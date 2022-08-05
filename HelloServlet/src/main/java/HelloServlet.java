import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//添加一个 servlet路径
@WebServlet("/hello")
//继承 HttpServlet类,以便后续重写一些方法
public class HelloServlet extends HttpServlet {
    @Override
//    doGet方法的作用：根据请求计算响应。
//    一个服务器的工作流程有三步：
//    1.收到请求并进行解析。（Tomcat帮我们实现了）
//    2.根据请求计算响应。（doGet方法）
//    3.通过响应构建数据并返回。（Tomcat帮我们实现了）
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpServletRequest req : Tomcat收到了请求并解析成了对象。
//        HttpServletResponse resp : 这是一个空对象,等待我们进行填充数据（添加一些属性）。
//        注释掉，不要它调用 父类 的doGet方法
        super.doGet(req, resp);

        resp.setContentType("text/html;charset=utf8");
        System.out.println("hello word!");

        resp.setHeader("refresh","1");
//      将 hello word 放到 http响应 的 body 中。浏览器会将其打印到页面上。
        resp.getWriter().write("hello word" + System.currentTimeMillis());
    }
}
