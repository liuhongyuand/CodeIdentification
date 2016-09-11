package com.louie.authcode.rest.model;

import com.louie.authcode.engine.config.EngineParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhongyu.louie on 2016/9/11.
 */
public class HTML {

    private String $SERVER = "";
    private String $IMAGE_PATH = "";
    private String $USER = "";
    private String $AFTER_ABSOLUTE_PATH = "";
    private String $BEFORE_ABSOLUTE_PATH = "";
    private Map<String, String> map = new HashMap<>();

    public void set$SERVER(String $SERVER) {
        this.$SERVER = $SERVER;
        map.put(EngineParameters.HTML.$SERVER, $SERVER);
    }

    public void set$IMAGE_PATH(String $IMAGE_PATH) {
        this.$IMAGE_PATH = $IMAGE_PATH;
        map.put(EngineParameters.HTML.$IMAGE_PATH, $IMAGE_PATH);
    }

    public void set$USER(String $USER) {
        this.$USER = $USER;
        map.put(EngineParameters.HTML.$USER, $USER);
    }

    public void set$AFTER_ABSOLUTE_PATH(String $AFTER_ABSOLUTE_PATH) {
        this.$AFTER_ABSOLUTE_PATH = $AFTER_ABSOLUTE_PATH;
        map.put(EngineParameters.HTML.$AFTER_ABSOLUTE_PATH, $AFTER_ABSOLUTE_PATH);
    }

    public void set$BEFORE_ABSOLUTE_PATH(String $BEFORE_ABSOLUTE_PATH) {
        this.$BEFORE_ABSOLUTE_PATH = $BEFORE_ABSOLUTE_PATH;
        map.put(EngineParameters.HTML.$BEFORE_ABSOLUTE_PATH, $BEFORE_ABSOLUTE_PATH);
    }

    public String replaceAll(String htmlContext){
        String[] context = {htmlContext};
        map.forEach((k, v) -> context[0] = context[0].replace(k, v));
        return context[0];
    }

}
