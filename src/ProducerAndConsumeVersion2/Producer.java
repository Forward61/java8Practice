package ProducerAndConsumeVersion2;

import java.util.List;
import java.util.Random;

/**
 * Created by freedom on 2019/1/27.
 */
public class Producer implements Runnable {
    private List<PCData> queue;
    private int length;

    public Producer(List<PCData> queue,int length){
        this.queue = queue;
        this.length = length;
    }

    @Override
    public void run() {
        try{
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    break;
                }
                Random r = new Random();
                long temp = r.nextInt(100);
                System.out.println(Thread.currentThread().getId() + " 生产了：" + temp);
                PCData data = new PCData();
                data.setValue(temp);
                synchronized (queue){
                    if(queue.size() >= length){
                        queue.notifyAll();
                        queue.wait();
                    }else {
                        queue.add(data);
                    }
                }
                Thread.sleep(100);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
