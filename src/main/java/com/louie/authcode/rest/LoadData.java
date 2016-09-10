package com.louie.authcode.rest;

import com.louie.authcode.engine.config.EngineParameters;
import com.louie.authcode.engine.model.Response;
import com.louie.authcode.engine.model.ResponseBody;
import com.louie.authcode.utils.TrainingREST;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
@RestController
@RequestMapping("/{userId}/*")
public class LoadData {

    private static boolean isLoaded = false;

    @RequestMapping(method = RequestMethod.GET, path = "/load")
    public Response loadData(@PathVariable("userId") String userId){
        ResponseBody responseBody = new ResponseBody("0", "");
        if (userId.toLowerCase().equals(EngineParameters.OWNER.toLowerCase())){
            if (!isLoaded){
                new TrainingREST().sendLearningREST();
                isLoaded = true;
                responseBody.setCode("0");
                responseBody.setMessage("The load command is submitted success.");
            } else {
                responseBody.setCode("1");
                responseBody.setMessage("The training data has been loaded.");
            }
        } else {
            responseBody.setCode("-1");
            responseBody.setMessage("You have no permission to call this interface.");
        }
        return responseBody;
    }

}
