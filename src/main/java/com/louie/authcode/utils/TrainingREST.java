package com.louie.authcode.utils;

import com.louie.authcode.engine.config.EngineParameters;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
public class TrainingREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingREST.class);
    private static final String URL = "http://104.236.158.103/authcode/training/";
    private static final String REQUEST_URL = "http://127.0.0.1:8080/louie/training/";

    public static void main(String[] args) {
        new TrainingREST().sendLearningREST();
    }

    public void sendLearningREST() {
        File[] files = new File(EngineParameters.PROJECT_ROOT + "/training").listFiles();
        for (File file : files != null ? files : new File[0]){
            ThreadSupport.threadPool.execute(() -> {
                List<Values> valuesList = new LinkedList<>();
                Values valueName = new Values("url", URL + file.getName());
                Values valueValue = new Values("authcode", file.getName().substring(0, file.getName().lastIndexOf(".")));
                valuesList.add(valueName);
                valuesList.add(valueValue);
                try {
                    Request.Post(REQUEST_URL).bodyForm(valuesList).execute();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });
        }
    }

    private class Values implements NameValuePair {

        private String name;
        private String value;

        Values(String name, String value){
            this.name = name;
            this.value = value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

}
