package com.louie.authcode.file;

import com.louie.authcode.file.model.AuthcodeFile;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
public interface FileService {

    AuthcodeFile downloadFile(AuthcodeFile file);

    AuthcodeFile delete(AuthcodeFile file);

    AuthcodeFile peekFile();

    void renameAndMove(AuthcodeFile file);

}
