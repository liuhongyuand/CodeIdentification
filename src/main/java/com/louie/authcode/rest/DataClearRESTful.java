package com.louie.authcode.rest;

import com.louie.authcode.engine.brain.PointMap;
import com.louie.authcode.engine.model.Response;
import com.louie.authcode.engine.model.ResponseBody;
import com.louie.authcode.rest.utils.RESTfulType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
@RestController
@RequestMapping(RESTfulType.USER)
public class DataClearRESTful {

    @RequestMapping(method = RequestMethod.POST, path = RESTfulType.CLEAR)
    public Response clearData(@PathVariable(value = RESTfulType.USER_PathVariable) String userId){
        PointMap.clearData();
        return new ResponseBody("1", "The data has been clear.");
    }

}
