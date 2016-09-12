package com.louie.authcode.rest;

import com.louie.authcode.engine.config.EngineParameters;
import com.louie.authcode.file.FileService;
import com.louie.authcode.file.FileServiceImpl;
import com.louie.authcode.file.model.AuthcodeFile;
import com.louie.authcode.rest.model.HTML;
import com.louie.authcode.rest.utils.HTMLUtil;
import com.louie.authcode.rest.utils.RESTfulType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

/**
 * Created by liuhongyu.louie on 2016/9/12.
 */
@RestController
@RequestMapping("/{userId}/*")
public class GetLearningPageRESTful {

    @RequestMapping(method = RequestMethod.GET, path = RESTfulType.LEARNING)
    public String trainingData(){
        FileService fileService = new FileServiceImpl();
        AuthcodeFile file = fileService.peekFile();
        HTML html = new HTML();
        html.set$SERVER(EngineParameters.Server);
        html.set$PORT(EngineParameters.Port);
        html.set$USER(EngineParameters.OWNER);
        html.set$ABSOLUTE_PATH(file.getFile().getAbsolutePath());
        InputStream stream = DisplayLearningImageRESTful.class.getClassLoader().getResourceAsStream("html/DisplayLearningData.html");
        return html.replaceAll(HTMLUtil.getHTML(stream));
    }

}