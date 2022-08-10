import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/thymeleaf")
public class ThymeleafServlet extends HttpServlet {

    //核心类,切实的负责模块渲染的工作（渲染：可以理解为 html 和 数据 的拼接）
    private TemplateEngine engine = new TemplateEngine();

    //对 TemplateEngine 进行初始化工作。主要就是创建 解析器，并设置一些属性。最后将 解析器 关联到 TemplateEngine 上。
    @Override
    public void init() throws ServletException {
        //在初始化时创建解析器,用来加载 html 页面并解析。
        //注意创建 解析器 对象时 需要指定一个 ServletContent对象,解析器 通过这个 对象 来获取文件的路径
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(this.getServletContext());

        //设置解析器的字符编码
        resolver.setCharacterEncoding("utf8");
        //设置具体文件路径,以及文件后缀名
        resolver.setPrefix("/WEB-INF/templates/");   //后面一定要有斜杠
        resolver.setSuffix(".html");

        //将 解析器 和 TemplateEngine 对象关联起来
        engine.setTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Get请求中的 QueryString 键值对的值。
        String message = req.getParameter("message");

        //创建一个 WebContext对象，里面包含了需要渲染的变量
        WebContext webContext = new WebContext(req, resp,this.getServletContext());

        //将模板中的 message变量渲染成 message的值。
        webContext.setVariable("message",message);

        //开始进行渲染操作
        //参数一：需要渲染的html文件
        //参数二：需要渲染的数据
        //参数三：将渲染后的html数据写到哪儿去
        engine.process("HelloThymeleaf",webContext, resp.getWriter());
    }
}
