package tokennizer;

import java.io.*;
import java.util.*;

/**
 * Created by freedom on 2019/7/14.
 */
public class Tokenizer {
    private Map<Character, Object> dictionary;

    public  Tokenizer(String dictonaryFilePath) throws IOException {
        dictionary = new TreeMap<>();

        this.loadDictionary(dictonaryFilePath);
    }

    public void loadDictionary(String dictonaryFilePath) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dictonaryFilePath)));
        String line = null;
        while ((line = reader.readLine()) != null){
            line = line.trim();
            if(line.length() == 0){
                continue;
            }
            char c;
            Map<Character, Object> child = this.dictionary;

            for (int i = 0;i < line.length(); i++){
                c= line.charAt(i);
                Map<Character, Object> ccMap = (Map<Character, Object>) child.get(c);
                if (ccMap == null){
                    ccMap = new HashMap<Character, Object>();
                    child.put(c,ccMap);
                }
                child = ccMap;
            }
        }
    }

    public List<String> participle(String text){
        if (text == null){
            return null;
        }

        text = text.trim();
        if (text.length() ==0){
            return null;
        }

        List<String> tokens = new ArrayList<>();
        char c;
        for (int i = 0;i <text.length();){
            StringBuilder token = new StringBuilder();
            Map<Character, Object> child = this.dictionary;
            boolean matchToken =false;

            for (int j = i;j <text.length();j++){
                c = text.charAt(j);
                token.append(c);
                Map<Character,Object> ccMap = (Map<Character, Object>) child.get(c);

                if (ccMap ==null){
                    break;
                }
                if (ccMap.isEmpty()){
                    matchToken = true;
                    i = j+1;
                    break;
                }
                child = ccMap;
            }
            if (matchToken){
                tokens.add(token.toString());
            }else {
                tokens.add("" + text.charAt(i));
                i++;
            }
        }
        return tokens;
    }

    public static void main(String[] args) throws IOException {
        File f = new File("/Users/ningli/Java8Practice/src/main/java/tokennizer/d.txt");
//        System.out.println("文件名：" + f.getName());
//        System.out.println("文件路径" + f.getPath());
//        System.out.println("绝对路径" + f.getAbsolutePath());
//        System.out.println("父文件夹名称" + f.getParent());
//        System.out.println(f.exists() ? "文件存在" : "文件不存在");
        Tokenizer tk = new Tokenizer(f.getPath());
//        Tokenizer tk = new Tokenizer(Tokenizer.class.getResource("Users/ningli/Java8Practice/src/main/java/d.txt").getPath());

        List<String> tokens = tk.participle("藏三说的确实在理，哈哈");
        for (String s:tokens) {
            System.out.println(s);
        }
    }
}
