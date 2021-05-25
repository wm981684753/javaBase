/** 多线程操作Demo*/
 //---线程生命周期-------------------
 //      1.创建线程对象
 //            |
 //            |start()
 //            |
 //      2.有执行资格,无执行权(就绪)
 //            |
 //            |抢到CPU执行权
 //            |
 //      3.有执行资格,有执行权(线程运行)
 //            |
 //            |run结束
 //            |stop()
 //            |
 //      4.线程死亡,变成垃圾(死亡)
 //*--------------------------------
public class ThreadDemo {
    public static void main(String[] args) {
        ThreadRun threadRun1 = new ThreadRun("线程1","参数1");
        ThreadRun threadRun2 = new ThreadRun("线程2","参数2");
        ThreadRun threadRun3 = new ThreadRun("线程3","参数3");

        //设置线程优先级1-10,越大优先级越高
        threadRun1.setPriority(1);
        threadRun2.setPriority(10);

        //获取线程优先级,默认是5
        int priority1 = threadRun1.getPriority();
        int priority2 = threadRun2.getPriority();
        int priority3 = threadRun3.getPriority();

        //执行线程
        threadRun1.start();
        try {
            threadRun1.join();//线程1执行完之后,其他线程才可以开始执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadRun2.start();
        threadRun3.start();
    }
}

class ThreadRun extends Thread {
    private String param1;

    public ThreadRun(){ }

    public ThreadRun(String name,String param1){
        super(name);
        this.param1 = param1;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            System.out.println(getName() + "-" + this.param1+"-"+i);
            try {
                Thread.sleep(1000);//线程休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
