
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java01?characterEncoding=utf8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    //使用懒加载的方式来进行数据库的连接(需要考虑线程安全问题)
    private volatile static DataSource dataSource = null;   //volatile 解决的是 指令重排序问题(实例化对象的会出现)

    //设置数据源
    private static DataSource getDataSource(){
        //双重if判断
        if(dataSource == null){              //判断是否需要加锁,只有第一次才需要加锁
            synchronized (JDBCUtils.class){       //将里面的判断和创建对象进行打包成一个整体
                if(dataSource == null){      //判断是否是第一次调用 getDataSource()
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setUrl(URL);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    //连接数据库
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    //释放资源操作
    public static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet){
        //先创建，最后释放。最后创建，先释放
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
