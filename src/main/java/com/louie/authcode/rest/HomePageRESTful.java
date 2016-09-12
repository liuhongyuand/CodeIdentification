package com.louie.authcode.rest;

import com.louie.authcode.rest.utils.HTMLUtil;
import com.louie.authcode.rest.utils.RESTfulType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
@RestController
public class HomePageRESTful {

    @RequestMapping(RESTfulType.HOME)
    public String welcome(){
        InputStream stream = DisplayLearningImageRESTful.class.getClassLoader().getResourceAsStream("html/HomePage.html");
        return HTMLUtil.getHTML(stream);
    }

}
