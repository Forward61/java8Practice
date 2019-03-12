package DesignPattern.Proxy;

/**
 * Created by freedom on 2019/3/12.
 */
public class Main {
    public static void main(String[] args) {
        SchoolGirl jiaojiao = new SchoolGirl();
        jiaojiao.setName("娇娇");
        ProxyClass daili = new ProxyClass(jiaojiao);

        daili.GiveDolls();
        daili.GIveFlowers();

    }
}
