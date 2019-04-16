package ProducerAndConsume;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

/**
 * Created by freedom on 2019/1/26.
 */
public class Consumer implements Runnable {

    private BlockingDeque<PCData> queue;
    private static  final  int SLEEPTIME = 1000;

    public Consumer(BlockingDeque<PCData> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        System.out.println("消费者Start consumer id :" + Thread.currentThread().getId());
        Random r = new Random();
        try{
            while (true){
                PCData data = queue.take();
                if (data != null){
                    int re = data.getDate() * data.getDate();
                    System.out.println(MessageFormat.format("消费者  获取的数据 {0} * {1} = {2}", data.getDate(), data.getDate(), re));;
                    Thread.sleep(r.nextInt(SLEEPTIME));

                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
