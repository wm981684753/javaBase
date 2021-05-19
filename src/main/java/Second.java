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
        for(int i=0;i<5;i++){
            str1.append(i+"");//字符串拼接操作不会产生占用新的变量池空间
        }
        str1.insert(1,"in");//指定位置插入字符串
        str1.replace(0,2,"replace");//按索引位置替换字符串
        str1.reverse();//字符串反转
        String s_str1 = str1.toString();//StringBuilder对象转String对象


        //String使用,不适合大量字符串拼接,适合字符串操作
        String str2 = "StringS";
        str2 = str2+"-a";//此时其实已经占用了一个新的栈空间，不适合大量拼接
        String str3 = str2.replace("S","s");//查找并替换字符串,返回一个新的对象
        int str2_length = str2.length();//获取字符串长度
        char ch = str2.charAt(1);//获取指定索引位置的字符串
        int compare_result1 = str2.compareTo(str3);//字符串大小比较，大小写铭感
        int compare_result2 = str2.compareToIgnoreCase(str3);//字符串大小比较，忽略大小写
        boolean eq1 = str2.equals(str3);//字符串是否相等比较,大小写敏感
        boolean eq2 = str2.equalsIgnoreCase(str3);//字符串是否相等比较,忽略大小写
        int index1 = str2.indexOf("St");//查询目标字符串在字符串中第一次出现的位置
        int index2 = str2.lastIndexOf("S");//查询目标字符串在字符串中最后一次出现的位置
        System.out.println(index1);
    }
}
