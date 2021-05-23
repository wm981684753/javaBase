import java.io.File;
import java.io.IOException;

//文件处理类
public class FileDemo {
    public static void main(String[] args) throws IOException {
        FileDemo fileDemo = new FileDemo();
        fileDemo.createFile();
//        fileDemo.isGet();
//        fileDemo.deleteFile();
//        fileDemo.eachDir();
    }

    /**
     * 创建文件
     */
    public void createFile() throws IOException {
        File file = new File("javaDir");
        file.mkdir();//创建目录

        File file1 = new File("javaDir/javaDir1");
        file1.mkdirs();//创建多级目录

        File file2 = new File("javaDir/javaDir1/java.txt");
        file2.createNewFile();//创建文件
    }

    /**
     * 文件判断和和获取
     */
    public void isGet() throws IOException {
        File file = new File("javaDir");
        boolean directory = file.isDirectory();//判断是否为文件夹

        File file1 = new File("javaDir/javaDir1/java.txt");
        boolean isFile = file1.isFile();//判断是否为文件
        boolean exists = file1.exists();//判断文件是否存在
        String absolutePath = file1.getAbsolutePath();//获取绝对路径
        File absoluteFile = file1.getAbsoluteFile();//获取绝对路径
        String canonicalPath = file1.getCanonicalPath();
        String path = file1.getPath();//获取相对路径
        String name = file1.getName();//获取文件名
        String[] list = file.list();//返回目录下包含的子目录及文件列表数组
        File[] files = file.listFiles();//返回目录下包含的子目录及文件[对象]列表数组

        System.out.println();
    }

    /**
     * 文件删除
     */
    public void deleteFile() {
        //删除文件
        //若要删除文件夹，需要先遍历删除文件夹下的所有文件后再删除空文件夹
        File file = new File("javaDir/javaDir1/java.txt");
        if (!file.exists()) {
            try {
                this.createFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            boolean delete = file.delete();
            System.out.println(delete);
        }
    }

    /**
     * 遍历目录（递归）
     */
    public void eachDir() {
        File file = new File("D:\\java\\javaBase");
        FileDemo.getEachPath(file);//调用递归方法
    }

    /**
     * 递归获取目录方法
     */
    private static void getEachPath(File file) {
        File[] files = file.listFiles();//先获取文件夹下所有文件及文件夹对象
        for (File file1 : files) {//遍历获取对象
            if(file1.isDirectory()){//对象是文件夹，递归调用
                FileDemo.getEachPath(file1);
            }else{
                String absolutePath = file1.getAbsolutePath();
                System.out.println(absolutePath);
            }
        }
    }
}
