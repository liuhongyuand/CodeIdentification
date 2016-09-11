package com.louie.authcode.rest;

import com.louie.authcode.rest.utils.HTMLUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

/**
 * Created by liuhongyu.louie on 2016/9/11.
 */
@RestController
@RequestMapping("/{userId}/*")
public class DisplayLearningData {

    @RequestMapping(method = RequestMethod.GET, path = "/display")
    public String displayLearningData(@PathVariable("userId") String userId) {
        InputStream stream = DisplayLearningData.class.getClassLoader().getResourceAsStream("html/DisplayLearningData.html");
        return HTMLUtil.getHTML(stream);
    }

}
