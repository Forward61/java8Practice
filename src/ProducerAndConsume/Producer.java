package ProducerAndConsume;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by freedom on 2019/1/26.
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingDeque<PCData> queue;//内存缓冲区
    private static AtomicInteger count = new AtomicInteger();//总数 原子操作
    private static final int SLEEPTIME = 1000;

    public Producer (BlockingDeque<PCData> queue){
        this.queue = queue;
    }


    @Override
    public void run() {
        PCData data = null;
        Random r = new Random();
        System.out.println("生产者Start produting id " + Thread.currentThread().getId());

        try {
            while (isRunning){
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                System.out.println("生产者" + data + "加入生产队列");
                if(!queue.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println(data + "加入队列失败");
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    public void stop(){
        isRunning = false;
    }
}
