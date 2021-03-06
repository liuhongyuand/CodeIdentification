package com.louie.authcode;

import com.louie.authcode.utils.FileDeleteUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by liuhongyu.louie on 2016/9/8.
 */
@SpringBootApplication
public class StartService {

    public static Boolean SystemIsOnline = true;

    static {
        FileDeleteUtil.initWatcher();
    }

    public static void main(String[] args){
        Object[] objects = new Object[]{StartService.class};
        SpringApplication.run(objects, args);
    }

    // TODO: 2016/9/11 Add learning module that can display all pic which in the waitTraining folder and has button to choose which should be accept.

}
