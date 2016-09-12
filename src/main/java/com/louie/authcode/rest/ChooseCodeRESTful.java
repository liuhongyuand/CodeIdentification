package com.louie.authcode.rest;

import com.louie.authcode.rest.utils.RESTfulType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuhongyu.louie on 2016/9/12.
 */
@RestController
@RequestMapping(RESTfulType.USER)
public class ChooseCodeRESTful {

    @RequestMapping(method = RequestMethod.POST, path = RESTfulType.CHOOSE)
    public String chooseFileAndGetNextFile(@RequestParam(value = "file_path", defaultValue = "") String processFilePath, @RequestParam(value = "rename", defaultValue = "") String rename){
        return processFilePath + " " + rename;
    }

}
