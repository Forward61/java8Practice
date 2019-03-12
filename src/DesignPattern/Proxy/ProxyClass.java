package DesignPattern.Proxy;

/**
 * Created by freedom on 2019/3/12.
 */
public class ProxyClass implements IGiveGift{
    Pursuit gg;

    public ProxyClass(SchoolGirl mm) {
        gg = new Pursuit(mm);
    }

    @Override
    public void GiveDolls() {
        gg.GiveDolls();
    }

    @Override
    public void GIveFlowers() {
        gg.GIveFlowers();
    }
}
