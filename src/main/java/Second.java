import sun.util.resources.CalendarData;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class Second extends First {
    String s_str = "second";

    public Second() {
        System.out.println("Second类--------");
    }

    /**
     * string处理类
     */
    public void string() {
        //StringBuilder使用,每次操作之后返回当前对象本身也不必接收，不占用额外空间
        StringBuilder str1 = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            str1.append(i + "");//字符串拼接操作不会产生占用新的变量池空间
        }
        str1.insert(1, "in");//指定位置插入字符串
        str1.replace(0, 2, "replace");//按索引位置替换字符串
        str1.reverse();//字符串反转
        String s_str1 = str1.toString();//StringBuilder对象转String对象


        //String使用,不适合大量字符串拼接,适合字符串操作
        String str2 = "StringS";
        str2 = str2 + "-a";//此时其实已经占用了一个新的栈空间，不适合大量拼接
        String str3 = str2.replace("S", "s");//查找并替换字符串,返回一个新的对象
        int str2_length = str2.length();//获取字符串长度
        char ch = str2.charAt(1);//获取指定索引位置的字符串
        int compare_result1 = str2.compareTo(str3);//字符串大小比较，大小写铭感
        int compare_result2 = str2.compareToIgnoreCase(str3);//字符串大小比较，忽略大小写
        boolean eq1 = str2.equals(str3);//字符串是否相等比较,大小写敏感
        boolean eq2 = str2.equalsIgnoreCase(str3);//字符串是否相等比较,忽略大小写
        int index1 = str2.indexOf("St");//查询目标字符串在字符串中第一次出现的位置
        int index2 = str2.lastIndexOf("S");//查询目标字符串在字符串中最后一次出现的位置
        String arr[] = str2.split("i");//字符串拆分，返回一个新的数组对象
        String con = str2.concat(str3);//合并字符串
        String lower = str2.toLowerCase();//字符串转为小写
        String up = str2.toUpperCase();//字符串转为大写
        String str5 = str2.substring(1, 5);//字符串截取
        StringBuilder stringBuilder = new StringBuilder(str2);//String对象转StringBuilder对象
    }

    /**
     * List集合操作
     */
    public void list() {
        //定义一个List集合
        List<Object> list = new ArrayList<Object>();
        list.add("string1");//添加元素
        list.add("string2");
        list.add("string3");
        list.add(123);
        //遍历集合元素
        for (Object o : list) {
//            System.out.println(o);
        }
        int size = list.size();//获取集合长度
        boolean isset = list.contains("string");//判断元素是否在集合中
        Object a = list.get(0);//获取元素
        list.remove(1);//按索引删除
        list.remove("string");//按元素值删除
        list.set(1, "string1-1");//改变指定位置元素的值
        int index = list.indexOf("string1");//查看元素第一次出现的位置索引
        int lIndex = list.lastIndexOf("string1");//查看元素最后一次出现的位置索引
        Object list2 = list.subList(0, 2);//截取集合，并将结果赋给另一个对象
        boolean isNull = list.isEmpty();//判断集合是否为空
        Iterator it = list.iterator();//转为指针对象
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
        String toString = list.toString();//转为String字符串
        Object toArray[] = list.toArray();//转为数组

        //集合去重1
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
                }
            }
        }

        //集合去重2 Collections.frequency：返回指定集合中指定对象出现的次数
        List<Object> list3 = new ArrayList<Object>();
        for (Object o : list) {
            if (Collections.frequency(list, o) < 1) {
                list3.add(o);
            }
        }
    }

    /**
     * Map集合操作
     */
    public void map() {
        //定义map键值对集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "string1");//添加元素
        map.put("key2", "string2");
        map.put("key3", "string3");
        boolean isKey = map.containsKey("key1");//集合中是否包含指定的key值
        boolean isVal = map.containsValue("string1");//集合中是否包含指定的val值
        Object val = map.get("key1");//根据key获取指定的val
        map.remove("key1");//根据key删除指定的元素（键值对）
        int size = map.size();//获取键值对集合长度
//        map.clear();//清空集合
        boolean isEmpty = map.isEmpty();//判断集合是否为空
        Set<String> keys = map.keySet();//返回所有key组成的集合
        Collection<Object> vals = map.values();//返回val集合
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();//返回键值对集合
        System.out.println(vals.toArray()[1]);

        //List\Set区别https://blog.csdn.net/cool_sti/article/details/17501145
    }

    /**
     * int与string互相转换
     */
    public void intString() {
        System.currentTimeMillis();
        String s = "100";
        int i = Integer.parseInt(s);
        String s2 = String.valueOf(i);
    }

    /**
     * date时间处理
     */
    public void date() {
        //Date日期类
        Date date = new Date();
        long time = date.getTime();
        long time2 = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_date = sdf.format(date);
        String f_date1 = sdf.format(time);
        String f_date2 = sdf.format(time2);
        System.out.println(f_date);

        //Calender日历类
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);//默认获取上个月，获取当前月得+1
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DATE);
        int h = calendar.get(Calendar.HOUR_OF_DAY);//注意此处使用24h制
        int i = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        System.out.println(y + "-" + m + "-" + d + " " + h + ":" + i + ":" + s);

        //获取当月最后一天
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DATE, calendar1.getActualMaximum(Calendar.DATE));//设置当前日期为当月的最后一天
        Date lastDay = calendar1.getTime();
        System.out.println(sdf.format(lastDay));
    }

    /**
     * Math类
     */
    public void math() {
        double d = 23.45;
        double ceil = Math.ceil(d);//向上取整
        double floor = Math.floor(d);//向下取整
        double round = Math.round(d);//四舍五入
        int i = (int)ceil;
        System.out.println(i);

        int ii = -23;
        int abs = Math.abs(ii);//取绝对值

        int max = Math.max(1,3);//取最大值
        double min = Math.min(1.2,3.4);//取最小值
    }
}
