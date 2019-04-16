import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("src/alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        int count = 0;
        for (String word : words){
            if (word.length()>10) {
                count++;
            }
        }
        System.out.println("-----------Test-----------count值=" + count + "," + "当前类=.()");


        //Stream练习
        long countLong = words.stream().filter(w->w.length()>10).count();
        System.out.println("-----------Test-----------countLong值=" + countLong + "," + "当前类=.()");

        long countParallerLong = words.stream().filter(w->w.length()>10).count();
        System.out.println("-----------Test-----------countParallerLong=" + countParallerLong + "," + "当前类=.()");

        Stream<String> uniqueWords = Stream.of("merrilly","merrilly","merrilly","goo").distinct();
        uniqueWords.forEach(System.out::println);

        Stream<String > song = Stream.of("gently", "down", "the", "stream");


        Stream<String> lowcaseWords = words.stream().map(String::toLowerCase);

        //optional类型

        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);
        int aInt = a.orElse(8);
        System.out.println("-----------Test-----------a值=" +a.orElse(6) + "," + "当前类=.()");





        List<String> name1 = new ArrayList<String>();
        name1.add("google");
        name1.add("Taobao");
        name1.add("baidu");
        List<String> name2 = new ArrayList<String>();
        name2.add("google");
        name2.add("Taobao");
        name2.add("jingdong");

        System.out.println("-----------Test-----------name1值=" + name1 + "," + "当前类=.()");
        sortUsingJava7(name1);
        System.out.println("-----------Test-----------name1值=" + name1 + "," + "当前类=.()");
        System.out.println("-----------Test-----------name1值=" + name2 + "," + "当前类=.()");

        sortUsingJava8(name2);
        System.out.println("-----------Test-----------name1值=" + name2 + "," + "当前类=.()");

        name1.forEach(str->System.out.println("-----------Test-----------str值=" + str + "," + "当前类=.()"));
        name2.forEach(System.out::println);

        filter("aaaa",str->str.length()==4);
        filter("aaaab",str->str.length()==4);

        testRunnable();
        Comparator<String> stringComparator = ( first,  second) -> {
            if (first.length() < second.length()) {
                return -1;
            }
            else if (first.length() > second.length()) {
                return 1;
            }
            else {return 0;}
        };
        System.out.println("-----------Test-----------stringComparator值=" + stringComparator.compare("12","2")+ "," + "当前类=.()");
        Comparator<String> stringComparator1 = (String fir, String sec) -> Integer.compare(fir.length(), sec.length());

        EventHandler<ActionEvent> listener = event -> System.out.println("Thanks for clicking");//无须（event）->或（ActionEvent event）->

        String [] strarr={"a","az","ss"};
        //函数式接口
        Arrays.sort(strarr, (String firstr, String secondstr) -> {
            return firstr.compareTo(secondstr);
        });
        int [] nums = {2,7,9,11,66};
            int [] results = twoSum(nums,9);
            for (int i = 0 ;i<results.length;i++){
                System.out.println("-----------Test-----------results[i]值=" + results[i] + "," + "当前类=.()");
            }
            System.out.println("Hello World!");
    }

    private static void sortUsingJava7(List<String> names){
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }
    private static void sortUsingJava8(List<String> names){
        Collections.sort(names,(s1,s2)->s1.compareTo(s2));
    }
    public static  int[] twoSum(int[] nums, int target) {
        int [] results = new int [2];
        int length = nums.length;

        for(int i = 0;i < length-1;i++){
            for (int j = i+1;j < length;j++){
                if (nums[i]+nums[j] == target){
                    results[0] = i;
                    results[1] = j;
                    break ;
                }

            }
        }
        return results;
    }
    //predicate断言
    public static void filter(String name, Predicate<String> condition){
        if(condition.test(name)){
            System.out.println("-----------Test-----------name值长度为4=" + "," + "当前类=.()");
        }else {
            System.out.println("-----------Test-----------name长度不为4值=" + "," + "当前类=.()");
        }
    }
    //Runnable
    public static void testRunnable(){
        Thread thread = new Thread(()->System.out.println("-----------Test-----------Runnable start值="   + "," + "当前类=.()"));
        thread.start();
    }

    //创建可选值
    public static Optional<Double> inverse(Double x){
        return x == 0 ? Optional.empty() : Optional.of(1/x);
    }
}
