public class Demo {
    String s_str = "demo";

    public static void main(String[] args) {
        Second fs = new Second();
//        fs.string();
//        fs.list();
//        fs.map();
//        fs.date();
        fs.math();
    }

    public String thisAttr(){
        return this.s_str;
    }
}
