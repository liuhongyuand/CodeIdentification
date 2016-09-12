package com.louie.authcode.rest;

import com.louie.authcode.engine.core.CodeIdentify;
import com.louie.authcode.file.FileService;
import com.louie.authcode.file.FileServiceImpl;
import com.louie.authcode.file.model.AuthcodeFile;
import com.louie.authcode.rest.utils.HTMLUtil;
import com.louie.authcode.rest.utils.RESTfulType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

/**
 * Created by liuhongyu.louie on 2016/9/11.
 */
@RestController
@RequestMapping(RESTfulType.USER)
public class DisplayLearningImageRESTful {

    @RequestMapping(method = RequestMethod.GET, path = RESTfulType.DISPLAY,produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] displayLearningData(@PathVariable(RESTfulType.USER_PathVariable) String userId) {
        FileService fileService = new FileServiceImpl();
        AuthcodeFile file = fileService.peekFile();
        CodeIdentify identify = new CodeIdentify();
        BufferedImage bufferedImage = identify.trainingPicIdentifyForGUI(file, false);
        return HTMLUtil.BufferedImageToBytes(bufferedImage);
    }

}
