import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class User{
    private String userId;
    private String classId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}

@WebServlet("/postJson")
public class PostGetJson extends HttpServlet {
    //创建 jackson的核心对象
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理json类型的数据

        //readValue:将一个 json 格式的字符串转换成一个 java对象
        //参数一：可以填 String,File对象,IO流
        //参数二：需要转成的 java对象
        User user = objectMapper.readValue(req.getInputStream(), User.class);
        resp.getWriter().write("userId = "+user.getUserId() + "  classId = "+user.getClassId());
    }
}
