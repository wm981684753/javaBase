import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlDemo {
    public static void main(String[] args) {
//        SqlDemo.selectDemo();
//        SqlDemo.insertDemo();
//        SqlDemo.deleteDemo();
        SqlDemo.updateDemo();

//        System.exit(1);
    }

    /**
     * 查询数据
     */
    public static void selectDemo() {
        //获取数据库操作类对象
        SqlConn sqlConn = SqlConn.getInstance();

        List<Map<String, Object>> result =sqlConn.select("select * from name_sign");
        System.out.println(result);
    }

    /**
     * 插入数据
     */
    public static void insertDemo() {
        //获取数据库操作类对象
        SqlConn sqlConn = SqlConn.getInstance();
        //组装数据
        List<Map<String, String>> insert_data = new ArrayList<Map<String, String>>();
        Map<String, String> insert1 = new HashMap<String, String>();
        insert1.put("name", "name1");
        insert1.put("sign", "sign1");
        Map<String, String> insert2 = new HashMap<String, String>();
        insert2.put("name", "name2");
        insert2.put("sign", "sign2");
        insert_data.add(insert1);
        insert_data.add(insert2);
        //一次插入多条数据
        boolean status = sqlConn.insertAll("name_sign", insert_data);
        if (status) {
            System.out.println("数据插入成功");
        } else {
            System.out.println("数据插入异常");
        }
    }

    /**
     * 修改数据
     */
    public static void updateDemo() {
        //获取数据库操作类对象
        SqlConn sqlConn = SqlConn.getInstance();

        Map<String,String> set = new HashMap<String, String>();
        set.put("name","up_name");
        set.put("sign","up_sign");
        boolean status = sqlConn.update("name_sign","id=2",set);
        if(status){
            System.out.println("数据更新成功");
        }else{
            System.out.println("数据更新异常");
        }
    }

    /**
     * 删除数据
     */
    public static void deleteDemo(){
        //获取数据库操作类对象
        SqlConn sqlConn = SqlConn.getInstance();
        boolean status = sqlConn.delete("name_sign","id=1");
        if(status){
            System.out.println("数据删除成功");
        }else{
            System.out.println("数据删除异常");
        }
    }
}
