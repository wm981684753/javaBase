import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

//IO流操作
//InputStreams输入流超类
//OutputStream输出流超类
public class IODemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        IODemo ioDemo = new IODemo();
//        ioDemo.fileOutput();
//        ioDemo.fileInput();
//        ioDemo.copyFile();
//        ioDemo.fileWrite();
//        ioDemo.fileRead();
        ioDemo.objectSerialize();
    }

    /**
     * 字节流数据写入文件
     * FileOutputStream文件输出流将数据写入文件File中
     */
    public void fileOutput() throws IOException {
        //创建字节输出流对象，会创建文件
//        FileOutputStream fileOutputStream = new FileOutputStream("javaDir/javaDir1/javaee.txt");//从文件头写入
        FileOutputStream fileOutputStream = new FileOutputStream("javaDir/javaDir1/javaee.txt", true);//追加写入

        //写入数据
        byte[] date1 = "你好".getBytes();//获取字符串对象的字节（如97：a，98:b）
        fileOutputStream.write(date1);//写入全部
        fileOutputStream.write(date1, 0, 3);//按字节写入部分数据

        //关闭文件，释放系统资源
        fileOutputStream.close();
    }

    /**
     * 字节流读数据
     * FileInputStream文件输入流读取数据
     */
    public void fileInput() throws IOException {
        //创建文件输入流对象
        FileInputStream fileInputStream = new FileInputStream("javaDir/javaDir1/javaee.txt");

        //字节流读数据
//        int read = fileInputStream.read();//数据流读数据,一次读一个字节
//        int read;
//        while ((read = fileInputStream.read()) != -1) {
//            System.out.println((char) read);
//        }

        byte[] bytes = new byte[5];
        int read1 = fileInputStream.read(bytes);
        System.out.println(read1);
        System.out.println(Arrays.toString(bytes));
    }

    /**
     * 复制文件
     */
    public void copyFile() throws IOException {
        //创建输入流对象,读取内容
        FileInputStream fileInputStream = new FileInputStream("javaDir/javaDir1/javaee.txt");
        //创建输出流对象,写数据
        FileOutputStream fileOutputStream = new FileOutputStream("javaDir/javaDir1/java.txt", true);

        //读取数据,并写入新文件
        int read;
        while ((read = fileInputStream.read()) != -1) {
            fileOutputStream.write(read);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

    /***********字符流************/
    /**
     * 字符流的好处是避免中文读取乱码
     * GBK一个中文占2个字节
     * UTF8一个中文占3个字节
     */

    /**
     * 字符流写入数据
     */
    public void fileWrite() throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("javaDir/javaDir1/javaee.txt", true));
        outputStreamWriter.write("魏铭");//写入数据
        outputStreamWriter.flush();//刷新流,刷新后数据才写入文件,刷新后可继续写入
        outputStreamWriter.close();//先刷新,后释放,释放后不可写入
    }

    /**
     * 字符流读数据
     */
    public void fileRead() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("javaDir/javaDir1/javaee.txt"));
//        int read;//一次读取一个字符
//        while ((read = inputStreamReader.read())!=-1){
//            System.out.println((char)read);
//        }

        char[] chars = new char[1024];//一次读取1024个字符
        int len;
        while ((len = inputStreamReader.read(chars)) != -1) {
            System.out.println(new String(chars, 0, len));
        }

//        int len = inputStreamReader.read(chars);
//        System.out.println(new String(chars,0,len));
    }

    /**
     * 对象序列化流
     * 注意:被序列化的对象必须实现Serializeable接口
     */
    public void objectSerialize() throws IOException, ClassNotFoundException {
        //创建对象流对象,参数为要写入的文件
        ObjectOutputStream serialize = new ObjectOutputStream(new FileOutputStream("javaDir/javaDir1/serialize.txt"));
        Second second = new Second();//需要序列化的对象
        serialize.writeObject(second);//序列化对象到文件中

        //创建反序列化对象
        ObjectInputStream unSerialize = new ObjectInputStream(new FileInputStream("javaDir/javaDir1/serialize.txt"));
        Object obj = unSerialize.readObject();//反序列化
        Second second1 = (Second)obj;//向下转型为Second类
        System.out.println(second1.s_str);
    }
}
