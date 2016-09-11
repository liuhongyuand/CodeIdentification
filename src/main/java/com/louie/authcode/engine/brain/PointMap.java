package com.louie.authcode.engine.brain;

import com.louie.authcode.engine.EngineConfiguration;
import com.louie.authcode.engine.brain.Identify.IdentificationService;
import com.louie.authcode.engine.brain.utils.FileUtils;

import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liuhongyu.louie on 2016/8/21.
 */
public class PointMap extends ConcurrentHashMap<String, HashSet<List<Point>>> implements Serializable{

    private static final long serialVersionUID = 1L;
    private static PointMap POINT_MAP = new PointMap();

    static {
        FileUtils.initPointMapWatcher();
        Object object = FileUtils.FileToObject();
        if (object != null){
            POINT_MAP = (PointMap) object;
        }
    }

    private PointMap(){
    }

    public static void clearData(){
        POINT_MAP.clear();
    }

    public static int mapSize(){
        return POINT_MAP.size();
    }

    public static void put(String letter, List<Point> pointList){
        if (POINT_MAP.containsKey(letter)) {
            POINT_MAP.get(letter).add(pointList);
        } else {
            HashSet<List<Point>> lists = new HashSet<>();
            lists.add(pointList);
            POINT_MAP.put(letter, lists);
        }
    }

    public static PointMap getPointMap(){
        return POINT_MAP;
    }

    public static String getAuthCode(List<?> letterSet){
        IdentificationService identificationService = EngineConfiguration.getService().getIdentificationService();
        final StringBuilder AUTHCODE= new StringBuilder("");
        letterSet.forEach((letter -> AUTHCODE.append(identificationService.identifyLetter((List<Point>) letter, POINT_MAP))));
        return AUTHCODE.toString();
    }

}