package com.louie.authcode.file;

import com.louie.authcode.engine.config.EngineParameters;
import com.louie.authcode.file.download.GetFile;
import com.louie.authcode.file.model.AuthcodeFile;

import java.io.File;
import java.nio.file.Files;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
public class FileServiceImpl implements FileService {

    private static final String TEMP_PATH = EngineParameters.PROJECT_ROOT + "/temp";

    @Override
    public AuthcodeFile downloadFile(AuthcodeFile file) {
        file.setStoragePath(TEMP_PATH);
        return GetFile.getFile(file);
    }

    @Override
    public AuthcodeFile delete(AuthcodeFile file) {
        return null;
    }

    @Override
    public AuthcodeFile peekFile() {
        AuthcodeFile file = new AuthcodeFile();
        File[] files = new File(EngineParameters.PROJECT_ROOT + "/waitTraining").listFiles();
        file.setFile(files != null ? files[0] : null);
        return file;
    }

    @Override
    public void rename(AuthcodeFile file) {

    }

}
