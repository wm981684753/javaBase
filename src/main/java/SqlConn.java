import javax.xml.transform.stream.StreamResult;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

//数据库操作Demo类
public class SqlConn {
    // 存储本类对象
    private static SqlConn sqlConn = null;
    // 数据库连接对象
    private static Connection connection = null;
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
     * 静态构造函数，防止外部创建对象
     */
    private SqlConn() {
    }

    /**
     * 公共的创建本类对象的唯一静态方法
     * 避免生成多个对象
     *
     * @return
     */
    public static SqlConn getInstance() {
        if (sqlConn == null) {
            sqlConn = new SqlConn();
        }
        return sqlConn;
    }

    /**
     * 创建并获取数据库连接对象
     *
     * @return
     */
    public Connection getCon() {
        if (connection == null) {
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
        }
        return connection;
    }

    /**
     * insertAll操作
     * 一次插入多条数据
     */
    public static int insertAll(String table, List<Map<String, String>> data) {
        if (connection == null) {
            SqlConn sqlConn = SqlConn.getInstance();
            connection = sqlConn.getCon();
        }
        try {
            //实例化Statement对象
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            //解析data数据,组合sql
            for (Map<String, String> map_data : data) {
                StringBuilder fields = new StringBuilder();
                StringBuilder values = new StringBuilder();
                Set<Map.Entry<String, String>> entrySet = map_data.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    String field = fields.length()>0?","+entry.getKey():entry.getKey();
                    fields.append(field);
                    String value = values.length()>0?","+"'"+entry.getValue()+"'":"'"+entry.getValue()+"'";
                    values.append(value);
                }
                String fields_string = fields.toString();
                String values_string = values.toString();
                String sql = "insert into name_sign("+fields_string+") values("+values_string+")";
                if(statement.executeUpdate(sql)!=1){
                    connection.rollback();
                }
            }
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }
}
