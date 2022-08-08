import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Message{
    private String who1;
    private String who2;
    private String content;

    public String getWho1() {
        return who1;
    }

    public void setWho1(String who1) {
        this.who1 = who1;
    }

    public String getWho2() {
        return who2;
    }

    public void setWho2(String who2) {
        this.who2 = who2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

@WebServlet("/message")
public class SubmitMessageServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    //用来存放用户提交的每组 message 数据
//    private List<Message> list = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        //将请求中的数据转化成 java对象
        Message message = objectMapper.readValue(req.getInputStream(), Message.class);

        //将数据添加进list中
//        list.add(message);
        submitMessage(message);

        //返回一个响应
        resp.setContentType("application/json; charset=utf8");  //告诉浏览器返回的响应的body是一个 json格式
        resp.getWriter().write("{ \"ok\": true }");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取list的数据返回给页面

        List<Message> list = getMessages();

        //该方法可以将java对象转换成一个json格式的字符串
        String s = objectMapper.writeValueAsString(list);
        System.out.println("json:" + s);
        resp.setContentType("application/json; charset=utf8");  //告诉浏览器返回的响应的body是一个 json格式
        resp.getWriter().write(s);
    }

    //将对象保存到数据库中
    private void submitMessage(Message message){
        //将其提出try外面来，方便在 finally 释放资源
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1.获取数据库连接
            connection = JDBCUtils.getConnection();
            //2.构造sql语句
            String sql = "insert into message values(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,message.getWho1());
            statement.setString(2,message.getWho2());
            statement.setString(3,message.getContent());
            System.out.println(statement);    //打印一下方便观察结果
            //3.执行sql语句
            int i = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //4.释放资源
            JDBCUtils.closeAll(connection,statement,null);
        }
    }

    //从数据库中获取Message数据
    private List<Message> getMessages(){
        //创建一个 ArrayList用来存放message
        List<Message> list = new ArrayList<>();
        //同样将其提出来方便进行释放资源
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.构造sql语句
            String sql = "select * from message";
            statement = connection.prepareStatement(sql);
            //3.执行sql语句
            resultSet = statement.executeQuery();
            //4.遍历结果集
            while(resultSet.next()){
                //5.创建一个 message对象
                Message message = new Message();
                message.setWho1(resultSet.getString("who1"));
                message.setWho2(resultSet.getString("who2"));
                message.setContent(resultSet.getString("content"));
                //6.将对象添加到List里
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7.释放资源
            JDBCUtils.closeAll(connection,statement,resultSet);
        }
        //8.返回结果
        return list;
    }
}
