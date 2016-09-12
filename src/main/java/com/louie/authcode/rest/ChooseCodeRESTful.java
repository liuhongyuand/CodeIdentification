package com.louie.authcode.rest;

import com.louie.authcode.file.FileService;
import com.louie.authcode.file.FileServiceImpl;
import com.louie.authcode.file.model.AuthcodeFile;
import com.louie.authcode.rest.utils.RESTfulType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Created by liuhongyu.louie on 2016/9/12.
 */
@RestController
@RequestMapping(RESTfulType.USER)
public class ChooseCodeRESTful {

    @RequestMapping(method = RequestMethod.POST, path = RESTfulType.CHOOSE)
    public String chooseFileAndGetNextFile(@RequestParam(value = "file_path", defaultValue = "") String processFilePath, @RequestParam(value = "rename", defaultValue = "") String rename){
        AuthcodeFile file = new AuthcodeFile();
        file.setFile(new File(processFilePath));
        if (!rename.equals("")){
            file.setAuthcode(rename);
            FileService fileService = new FileServiceImpl();
            fileService.renameAndMove(file);
        } else {
            if (file.getFile() != null){
                file.getFile().delete();
            }
        }
        return "OK";
    }

}
