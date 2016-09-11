package com.louie.authcode.rest;

import com.louie.authcode.rest.utils.HTMLUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
@RestController
public class HomePage {

    @RequestMapping("/")
    public String welcome(){
        InputStream stream = DisplayLearningData.class.getClassLoader().getResourceAsStream("html/HomePage.html");
        return HTMLUtil.getHTML(stream);
    }

}
