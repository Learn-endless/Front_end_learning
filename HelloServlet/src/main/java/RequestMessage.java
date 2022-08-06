import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/showRequest")
public class RequestMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用来存放拿到的 请求 数据
        StringBuilder stringBuilder = new StringBuilder();

        //获取协议版本号
        String protocol = req.getProtocol();
        stringBuilder.append(protocol);
        //添加换行
        stringBuilder.append("<br>");


        //获取请求方法
        String method = req.getMethod();
        stringBuilder.append(method);
        //添加换行
        stringBuilder.append("<br>");

        //获取请求URL
        String requestURI = req.getRequestURI();
        stringBuilder.append(requestURI);
        //加换行
        stringBuilder.append("<br>");

        //获取QueryString
        String queryString = req.getQueryString();
        stringBuilder.append(queryString+"<br>");

        //获取 Content Path路径
        String contextPath = req.getContextPath();
        stringBuilder.append(contextPath+"<br>");

        stringBuilder.append("header部分:<br>");

        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            String header = req.getHeader(s);
            stringBuilder.append(s + " : " + header + "<br>");
        }

        //设置响应 body 的类型为 html,同时字符编码为 utf8
        resp.setContentType("text/html;charset=utf8");
        //将数据写到 response 的 body 中
        resp.getWriter().write(stringBuilder.toString());
    }
}
