import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

//数据库简单操作模型类
//之后可以改为封装构造器方法
//table(),filed(),where(),order(),limit(),select(),update(),delete()...
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
     * @return 当前类对象
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
     * @return Connection实例
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
    public boolean insertAll(String table, List<Map<String, String>> data) {
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
                    String field = fields.length() > 0 ? "," + entry.getKey() : entry.getKey();
                    fields.append(field);
                    String value = values.length() > 0 ? "," + "'" + entry.getValue() + "'" : "'" + entry.getValue() + "'";
                    values.append(value);
                }
                String fields_string = fields.toString();
                String values_string = values.toString();
                String sql = "insert into name_sign(" + fields_string + ") values(" + values_string + ")";
                if (statement.executeUpdate(sql) != 1) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 查询数据，返回结果集
     * 此方法直接执行sql，稍后可以封装组合调用构造器table.field.where.order.limit
     *
     * @param sql sql语句
     * @return 数据集合
     */
    public List<Map<String, Object>> select(String sql) {
        if (connection == null) {
            SqlConn sqlConn = SqlConn.getInstance();
            connection = sqlConn.getCon();
        }
        // 创建List集合存储结果集
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            //实例化Statement对象
            Statement statement = connection.createStatement();
            //查询使用executeQuery()
            ResultSet res = statement.executeQuery(sql);//执行sql
            //将结果集放入集合
            Map<String, List<String>> result = new HashMap<String, List<String>>();

            // 获取结果集中的元数据
            ResultSetMetaData metaData = res.getMetaData();
            // 字段的个数
            int count = metaData.getColumnCount();

            // 处理结果集
            while (res.next()) {
                // 创建Map集合
                Map<String, Object> map = new HashMap<String, Object>();
                // 根据字段的个数, 循环
                for (int i = 0; i < count; i++) {
                    // 获取字段的名字
                    String columnName = metaData.getColumnName(i + 1);
                    // 获取字段对应的值
                    Object object = res.getObject(columnName);
                    // 将字段名和字段值, 存入map集合中
                    map.put(columnName, object);
                }

                // 将map集合添加到List集合中
                list.add(map);
            }

            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 删除数据
     *
     * @param table 表名
     * @param where 条件
     * @return 删除结果
     */
    public boolean delete(String table, String where) {
        if (connection == null) {
            SqlConn sqlConn = SqlConn.getInstance();
            connection = sqlConn.getCon();
        }
        try {
            //实例化Statement对象
            Statement statement = connection.createStatement();
            //组装sql
            String sql = "delete from " + table + " where " + where;
            //执行sql
            int status = statement.executeUpdate(sql);
            if (status == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 修改数据
     * @param table 表名
     * @param where 条件
     * @param set 要修改的数据集合<field，value>
     * @return 修改结果
     */
    public boolean update(String table,String where,Map<String,String> set) {
        if (connection == null) {
            SqlConn sqlConn = SqlConn.getInstance();
            connection = sqlConn.getCon();
        }
        try {
            //实例化Statement对象
            Statement statement = connection.createStatement();

            //组合sql
            StringBuilder sql_b = new StringBuilder();
            sql_b.append("update ");
            sql_b.append(table);
            sql_b.append(" set ");
            Set<Map.Entry<String,String>> entrySet = set.entrySet();
            for (Map.Entry<String,String> data:entrySet){
                String field = data.getKey();
                String value = data.getValue();
                String set_s = field+"='"+value+"',";
                sql_b.append(set_s);
            }

            //删除最后一个“,”
            sql_b.reverse();
            sql_b.replace(0,1,"");
            sql_b.reverse();

            sql_b.append(" where ");
            sql_b.append(where);
            String sql = sql_b.toString();
            int status = statement.executeUpdate(sql);
            if(status==1){
                return true;
            }else{
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
