package Hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by freedom on 2019/7/20.
 */
@Service
public class CommService {

    @Autowired
    QueryServiceRemoteCall queryServiceRemoteCall;



    class Request{
        String commid;
        CompletableFuture <Map<String,Object>> future;
    }
    LinkedBlockingDeque<Request> queue = new LinkedBlockingDeque<>();

    @PostConstruct
    public void init(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            int size = queue.size();
            if (size == 0) {
                return;
            }
            ArrayList<Request> requests = new ArrayList<>();
            for (int i = 0; i< size;i++){
                Request request = queue.poll();
                requests.add(request);
            }
            System.out.println("当前批量处理了"+ size +"个请求");
            ArrayList<String> commCodes = new ArrayList<>();
            for (Request request:requests){
                commCodes.add(request.commid);
            }

            List<Map<String, Object>> responses = queryServiceRemoteCall.queryCommByCodeBatch(commCodes);

            HashMap<String,Map<String,Object>> responseMap = new HashMap<>();
            for (Map<String,Object> response : responses){
                String code = response.get("code").toString();
                responseMap.put(code,response);
            }
            for (Request request : requests){
                Map<String ,Object> rs= responseMap.get(request.commid);
                request.future.complete(rs);
            }


        },0,10, TimeUnit.MILLISECONDS);

    }





    public Map<String, Object> queryComm(String commCode) throws ExecutionException, InterruptedException {

        Request request = new Request();
        request.commid = commCode;

        CompletableFuture<Map<String,Object>> completableFuture = new CompletableFuture<>();


        request.future= completableFuture;
        queue.add(request);
        return completableFuture.get();
    }


}
