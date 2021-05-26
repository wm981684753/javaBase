import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

//数据库操作Demo类
public class SqlConn {
    // 数据库连接对象
    private static Connection connection;
    // 数据库连接地址
    private static String path = "jdbc:mysql://127.0.0.1:3306";
    // 数据库名称
    private static String database = "javabase";
    // 数据库编码
    private static String characterEncoding = "utf8";
    // 是否使用SSL
    private static String useSSL = "true";
    // 数据库的用户名
    private static String userName = "root";
    // 数据库的密码
    private static String password = "root";

    /**
     * 无参构造函数，使用默认配置
     */
    public SqlConn(){}

    /**
     * 全参数构造函数，自定义连接参数
     * @param path
     * @param database
     * @param characterEncoding
     * @param useSSL
     * @param userName
     * @param password
     */
    public SqlConn(String path,String database,String characterEncoding,String useSSL,String userName,String password){
        if(path.length()>0){
            SqlConn.path = path;
        }
        if(database.length()>0){
            SqlConn.database = path;
        }
        if(characterEncoding.length()>0){
            SqlConn.characterEncoding = path;
        }
        if(useSSL.length()>0){
            SqlConn.useSSL = path;
        }
        if(userName.length()>0){
            SqlConn.userName = path;
        }
        if(password.length()>0){
            SqlConn.password = path;
        }
    }

    /**
     * 创建并获取数据库连接对象
     * @return
     */
    public Connection getCon() {
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动创建");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String url;//数据库连接url
            url = path + "/" + database + "?" + characterEncoding + "&" + useSSL;

            //获取数据库连接对象
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("数据库连接成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
