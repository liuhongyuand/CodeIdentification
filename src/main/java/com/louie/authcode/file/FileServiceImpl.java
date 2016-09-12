package com.louie.authcode.file;

import com.louie.authcode.engine.config.EngineParameters;
import com.louie.authcode.file.download.GetFile;
import com.louie.authcode.file.model.AuthcodeFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
public class FileServiceImpl implements FileService {

    private static final String TEMP_PATH = EngineParameters.PROJECT_ROOT + "/temp";
    private static final String TEMP_LEARNING_PATH = EngineParameters.PROJECT_ROOT + "/tempLearning";
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

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
    public void renameAndMove(AuthcodeFile file) {
        File newFile = file.getFile();
        File afterMoveFile = new File(TEMP_LEARNING_PATH + "/" + newFile.getName());
        File afterRename = new File(TEMP_LEARNING_PATH + "/" + file.getAuthcode() + ".jpg");
        try {
            Files.move(newFile.toPath(), afterMoveFile.toPath());
            StringBuilder zeros = new StringBuilder("0");
            while (afterRename.exists()){
                afterRename = new File(TEMP_LEARNING_PATH + "/" + file.getAuthcode() + zeros.toString() + ".jpg" );
                zeros.append("0");
            }
            afterMoveFile.renameTo(afterRename);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
