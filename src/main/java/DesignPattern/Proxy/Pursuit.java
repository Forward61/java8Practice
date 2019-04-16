package DesignPattern.Proxy;

/**
 * Created by freedom on 2019/3/12.
 */
public class Pursuit implements IGiveGift{
    SchoolGirl mm;

    public Pursuit(SchoolGirl mm) {
        this.mm = mm;
    }

    @Override
    public void GiveDolls() {
        System.out.println(mm.getName() +"送你洋娃娃。");
    }

    @Override
    public void GIveFlowers() {
        System.out.println(mm.getName() + "送你鲜花");

    }
}
