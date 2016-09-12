package com.louie.authcode.file.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
public class AuthcodeFile {

    private String url;
    private String storagePath;
    private String authcode;
    private URL imageUrl;
    private File file;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthcodeFile.class);

    public AuthcodeFile(){

    }

    public AuthcodeFile(String url){
        setUrl(url);
    }

    public AuthcodeFile(String url, String authcode){
        setUrl(url);
        setImageUrl(url);
        setAuthcode(authcode);
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        try {
            this.imageUrl = (new URL(imageUrl));
        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
