package DesignPattern;

/**
 * Created by freedom on 2019/3/16.
 * 可变参数列表，具有可选的尾随参数
 */
public class OptionalTrailingArguments {
    static void f(int required,String ... trailing){
        System.out.print("required: " + required +",");
        for (String s : trailing){
            System.out.println(  "s:" +s );
        }
        System.out.println();
    }

    public static void main(String[] args) {
        f(1,"one");
        f(2,"two","three");
        f(0);
        f(4,"four");
    }
}
