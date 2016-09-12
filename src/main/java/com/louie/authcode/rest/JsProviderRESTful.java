package com.louie.authcode.rest;

import com.louie.authcode.rest.utils.HTMLUtil;
import com.louie.authcode.rest.utils.RESTfulType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

/**
 * Created by liuhongyu.louie on 2016/9/12.
 */
@RestController
public class JsProviderRESTful {

    @RequestMapping(path = RESTfulType.JS)
    public byte[] getJs(@PathVariable String js){
        InputStream inputStream = DisplayLearningImageRESTful.class.getClassLoader().getResourceAsStream("js/" + js + ".js");
        return HTMLUtil.InputStreamToBytes(inputStream);
    }

}
