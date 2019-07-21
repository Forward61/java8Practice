package Hystrix.service;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author freedom
 * @date 2019/7/20
 */
@Service
public class QueryServiceRemoteCall  {

    public HashMap<String,Object> queryCommByCode(String code){
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("commid",new Random().nextInt(9999));
        hashMap.put("code",code);
        hashMap.put("phone","huawei");
        hashMap.put("price","4000");



        return hashMap;
    }

    public List<Map<String,Object>> queryCommByCodeBatch(List<String> codeList){
        ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String code :codeList){
            HashMap<String,Object> hashMap = new HashMap<String,Object>();
            hashMap.put("commid",new Random().nextInt(9999));
            hashMap.put("code",code);
            hashMap.put("phone","huawei");
            hashMap.put("price","4000");
            arrayList.add(hashMap);
        }





        return arrayList;
    }
}
