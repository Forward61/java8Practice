package Hystrix.web;

import Hystrix.service.CommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by freedom on 2019/7/20.
 */
@RestController
public class CommApiController {
    @Autowired
    CommService commService;

    @RequestMapping("movie/query")
    public Map<String,Object> queryMovie(String code) throws ExecutionException, InterruptedException {
        return commService.queryComm(code);
    }


}
