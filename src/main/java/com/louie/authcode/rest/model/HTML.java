package com.louie.authcode.rest.model;

import com.louie.authcode.engine.config.EngineParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhongyu.louie on 2016/9/11.
 */
public class HTML {

    private String $SERVER = "";
    private String $PORT = "";
    private String $USER = "";
    private String $RESULT = "";
    private String $ABSOLUTE_PATH = "";
    private Map<String, String> map = new HashMap<>();

    public void set$SERVER(String $SERVER) {
        this.$SERVER = $SERVER;
        map.put(EngineParameters.HTML.$SERVER, $SERVER);
    }

    public void set$PORT(String $PORT) {
        this.$PORT = $PORT;
        map.put(EngineParameters.HTML.$PORT, $PORT);
    }

    public void set$RESULT(String $RESULT) {
        this.$RESULT = $RESULT;
        map.put(EngineParameters.HTML.$RESULT, $RESULT);
    }

    public void set$USER(String $USER) {
        this.$USER = $USER;
        map.put(EngineParameters.HTML.$USER, $USER);
    }

    public void set$ABSOLUTE_PATH(String $ABSOLUTE_PATH) {
        this.$ABSOLUTE_PATH = $ABSOLUTE_PATH;
        map.put(EngineParameters.HTML.$ABSOLUTE_PATH, $ABSOLUTE_PATH);
    }

    public String replaceAll(String htmlContext){
        String[] context = {htmlContext};
        map.forEach((k, v) -> context[0] = context[0].replace(k, v));
        return context[0];
    }

}
