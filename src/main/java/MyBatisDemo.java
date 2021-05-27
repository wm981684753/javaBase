import com.junly.mybatis.beans.NameSing;
import com.junly.mybatis.dao.NameSignDao;

public class MyBatisDemo {
    public static void main(String[] args){
        NameSignDao nameSignDao = new NameSignDao();
        NameSing nameSing = new NameSing();
        nameSing.setName("mybatis_name");
        nameSing.setSign("mybatis_sign");
        //使用mapper接口
        int status = nameSignDao.insert(nameSing);
        if(status==1){
            System.out.println("insert success");
        }else{
            System.out.println("insert fail");
        }
    }
}
