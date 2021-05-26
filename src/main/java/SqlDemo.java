import sun.misc.Request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlDemo {
    public static void main(String[] args) {
        //获取数据库操作类对象
        SqlConn sqlConn = SqlConn.getInstance();
        //获取数据库连接对象
        Connection connection = sqlConn.getCon();

        try {
            Statement mysql = connection.createStatement();// 实例化Statement对象

            //查询使用executeQuery()
            ResultSet res = mysql.executeQuery("select * from name_sign");//执行sql
            //将结果集放入集合
            Map<String,List<String>> result = new HashMap<String,List<String>>();
            while (res.next()){
                List<String> list = new ArrayList<String>();
                list.add(res.getString("id"));
                list.add(res.getString("name"));
                list.add(res.getString("sign"));
                result.put(res.getString("id"),list);
            }
            System.out.println(result);

            //插入数据
            List<Map<String,String>> insert_data = new ArrayList<Map<String, String>>();
            Map<String,String> insert1 = new HashMap<String, String>();
            insert1.put("name","name1");
            insert1.put("sign","sign1");
            Map<String,String> insert2 = new HashMap<String, String>();
            insert2.put("name","name2");
            insert2.put("sign","sign2");
            insert_data.add(insert1);
            insert_data.add(insert2);
            int insert = sqlConn.insertAll("name_sign",insert_data);

            //增删改使用executeUpdate()
            int res2 = mysql.executeUpdate("update name_sign set sign='phper' where id=1");
            if(res2==1) {
                System.out.println("修改数据成功");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
