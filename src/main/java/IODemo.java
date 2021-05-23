import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//IO流操作
//InputStreams输入流超类
//OutputStream输出流超类
public class IODemo {
    public static void main(String[] args) throws IOException {
        IODemo ioDemo = new IODemo();
//        ioDemo.fileOutput();
        ioDemo.fileInput();
//        ioDemo.copyFile();
    }

    /**
     * 数据写入文件
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

        StringBuffer sb = new StringBuffer();
        for (int k : bytes) {
            if (sb.length() < 1) {
                sb.append(String.format("%d", k));
            } else {
                sb.append(String.format(",%d", k));
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * 复制文件
     */
    public void copyFile() throws IOException {
        //创建输入流对象,读取内容
        FileInputStream fileInputStream = new FileInputStream("javaDir/javaDir1/javaee.txt");
        //创建输出流对象,写数据
        FileOutputStream fileOutputStream = new FileOutputStream("javaDir/javaDir1/java.txt",true);

        //读取数据,并写入新文件
        int read;
        while ((read = fileInputStream.read()) != -1) {
            fileOutputStream.write(read);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
