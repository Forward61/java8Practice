package ProducerAndConsume;

import java.util.SplittableRandom;

/**
 * 容器数据类型
 * Created by freedom on 2019/1/26.
 *
 */
public class PCData {
    private final int intData;
    public PCData(int d){
        intData = d;
    }

    public PCData(String d){
        intData = Integer.valueOf(d);
    }
    public int getDate(){
        return intData;
    }

    @Override
    public String toString() {
        return "data : " + intData;
    }
}
