package Hystrix;

import Hystrix.service.CommService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by freedom on 2019/7/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HystrixDemoTest {

    //模拟的请求数量
    private static  final int THREAD_NUM = 1000;
    //倒计数器 juc包中常用工具类
    private static CountDownLatch countDownLatch =  new CountDownLatch(THREAD_NUM);


    @Autowired
    private  CommService commService;

//    private static HystrixDemoTest  hystrixDemoTest ;
//
//    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
//    public void init() {
//        hystrixDemoTest = this;
//        hystrixDemoTest.commService = this.commService;
//        // 初使化时将已静态化的testService实例化
//    }


    @Test
    public void benchmark() throws IOException {
        //创建，并不是马上发起请求
        for (int i = 0;i<THREAD_NUM;i++){
            final String code = "code-" +(i+1);
            //多线程模拟用户查询请求
            Thread thread = new Thread(() -> {
                try {
                    //代码在这里等待，等待countDownLatch为0，代表所有线程都start，再执行后面的代码
                    countDownLatch.await();
                    Map<String,Object > result =  commService.queryComm(code);
                    System.out.println(result.get("commid") + "   " +result.get("code"));
                }catch (Exception e){
                    System.out.println(Thread.currentThread().getName()+"进程出现问题"+e.getMessage());
                }
            });
            thread.setName("price-thread-" + code);;
            thread.start();
            //启动后，倒计时器倒计数减一，代表又有一个线程就绪了
            countDownLatch.countDown();


        }
//        System.in.read();
    }

//    public static void main(String[] args) {
//        //创建，并不是马上发起请求
//        for (int i = 0;i<THREAD_NUM;i++){
//            final String code = "code-" +(i+1);
//            //多线程模拟用户查询请求
//            Thread thread = new Thread(() -> {
//                try {
//                    //代码在这里等待，等待countDownLatch为0，代表所有线程都start，再执行后面的代码
//                    countDownLatch.await();
//                    Map<String,Object > result = commService.queryComm(code);
//                }catch (Exception e){
//                    System.out.println(Thread.currentThread().getName()+"进程出现问题"+e.getMessage());
//                }
//            });
//            thread.setName("price-thread-" + code);;
//            thread.start();
//            //启动后，倒计时器倒计数减一，代表又有一个线程就绪了
//            countDownLatch.countDown();
//
//
//        }
//    }
}
