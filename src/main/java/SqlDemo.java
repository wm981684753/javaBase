import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDemo {
    public static void main(String[] args) {
        SqlConn sqlConn = new SqlConn();
        Connection connection = sqlConn.getCon();
        System.out.println(connection);

        try {
            Statement mysql = connection.createStatement();// 实例化Statement对象
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
