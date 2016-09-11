package com.louie.authcode.rest;

import com.louie.authcode.engine.config.EngineParameters;
import com.louie.authcode.engine.core.CodeIdentify;
import com.louie.authcode.file.FileService;
import com.louie.authcode.file.FileServiceImpl;
import com.louie.authcode.file.model.AuthcodeFile;
import com.louie.authcode.rest.model.HTML;
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
        FileService fileService = new FileServiceImpl();
        AuthcodeFile file = fileService.peekFile();
        HTML html = new HTML();
        html.set$SERVER(EngineParameters.Server);
        html.set$USER(EngineParameters.OWNER);
        CodeIdentify identify = new CodeIdentify();
        file = identify.trainingPicIdentifyForGUI(file, false);
        html.set$IMAGE_PATH("http://" + EngineParameters.Server + "/waitTraining/" + file.getWebPathFile().getName());
        html.set$AFTER_ABSOLUTE_PATH(file.getWebPathFile().getAbsolutePath());
        html.set$BEFORE_ABSOLUTE_PATH(file.getFile().getAbsolutePath());
        InputStream stream = DisplayLearningData.class.getClassLoader().getResourceAsStream("html/DisplayLearningData.html");
        return html.replaceAll(HTMLUtil.getHTML(stream));
    }

}
