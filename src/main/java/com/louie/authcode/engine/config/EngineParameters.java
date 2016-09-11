package com.louie.authcode.engine.config;

import com.louie.authcode.utils.ImportFileUtils;


/**
 * Created by liuhongyu.louie on 2016/9/3.
 */
public final class EngineParameters {

    public static final String PROJECT_ROOT = System.getProperties().getProperty("user.dir");
    public static final String CodeIdentifyDataPath = PROJECT_ROOT  + "/data/CodeIdentifyData.data";
    public static final String WebPath = ImportFileUtils.getPropertiesParameters().getProperty("WebPath");      //Web path
    public static final String Server = ImportFileUtils.getPropertiesParameters().getProperty("Server");      //Web path

    public static final String OWNER = "Louie";
    public static final double similarity = Double.parseDouble(ImportFileUtils.getPropertiesCodeIdentify().getProperty("similarity"));      //相似程度
    public static final double deviation = Double.parseDouble(ImportFileUtils.getPropertiesCodeIdentify().getProperty("deviation"));    //误差系数
    public static final int difRate = Integer.parseInt(ImportFileUtils.getPropertiesCodeIdentify().getProperty("difRate"));           //色值偏移
    public static final double eliminateValue = Double.parseDouble(ImportFileUtils.getPropertiesCodeIdentify().getProperty("eliminateValue"));     //过滤没有验证码的模块
    public static final int target = Integer.parseInt(ImportFileUtils.getPropertiesCodeIdentify().getProperty("target"));             //黑色
    public static final int nearby = Integer.parseInt(ImportFileUtils.getPropertiesCodeIdentify().getProperty("nearby"));                    //像素位
    public static final int LETTER_WIDTH = Integer.parseInt(ImportFileUtils.getPropertiesCodeIdentify().getProperty("LETTER_WIDTH"));             //字母宽度
    public static final int LETTER_GAP = Integer.parseInt(ImportFileUtils.getPropertiesCodeIdentify().getProperty("LETTER_GAP"));               //字母间隔
    public static final double pixelFilter = Double.parseDouble(ImportFileUtils.getPropertiesCodeIdentify().getProperty("pixelFilter"));         //像素过滤

    public static final class HTML{
        public static final String $SERVER = "$SERVER";
        public static final String $IMAGE_PATH = "$IMAGE_PATH";
        public static final String $USER = "$USER";
        public static final String $AFTER_ABSOLUTE_PATH = "$AFTER_ABSOLUTE_PATH";
        public static final String $BEFORE_ABSOLUTE_PATH = "$BEFORE_ABSOLUTE_PATH";
    }

}
