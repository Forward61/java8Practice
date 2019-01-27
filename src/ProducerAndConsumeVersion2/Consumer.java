package ProducerAndConsumeVersion2;

import java.util.List;
/**
 * Created by freedom on 2019/1/27.
 */
public class Consumer implements Runnable {

    private List<PCData> queue;
    public Consumer(List<PCData> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    break;
                }
                PCData data = null;
                synchronized (queue){
                    if (queue.size() == 0){
                        queue.wait();
                        queue.notifyAll();
                    }
                    data = queue.remove(0);

                }
                System.out.println(
                        Thread.currentThread().getId() + " 消费了:" + data.getValue() + " result:" + (data.getValue() * data.getValue()));
                Thread.sleep(1000);
            }
        }catch (Exception exection){
            exection.printStackTrace();
        }
    }
}
