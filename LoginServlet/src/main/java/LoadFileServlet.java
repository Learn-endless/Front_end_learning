import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig         //一定不能忘了这个,不然在 获取文件 时就会抛异常
@WebServlet("/load")
public class LoadFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //每一个Part对象就表示一个文件
        Part myFile = req.getPart("MyFile");
        //获取文件的名字
        String submittedFileName = myFile.getSubmittedFileName();
        System.out.println(submittedFileName);
        //获取文件的类型
        String contentType = myFile.getContentType();
        System.out.println(contentType);
        //获取文件的大小
        long size = myFile.getSize();
        System.out.println(size);
        //将文件写入本地硬盘
        myFile.write("F:/aaa.png");
        resp.setContentType("text/html;charset=utf8");
        resp.getWriter().write("上传成功!");
    }
}
