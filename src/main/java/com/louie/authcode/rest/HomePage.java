package com.louie.authcode.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
@RestController
public class HomePage {

    @RequestMapping("/")
    public String welcome(){
        return "<center><p>Welcome to use authcode identification service.</center>";
    }

}
