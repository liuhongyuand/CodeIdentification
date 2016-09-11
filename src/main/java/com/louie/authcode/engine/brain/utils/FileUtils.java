package com.louie.authcode.engine.brain.utils;

import com.louie.authcode.StartService;
import com.louie.authcode.engine.brain.PointMap;
import com.louie.authcode.engine.config.EngineParameters;
import com.louie.authcode.utils.ThreadSupport;
import com.louie.authcode.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liuhongyu.louie on 2016/8/21.
 */
public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();

    public static void ObjectToFile(Object object){
        if (object == null){
            return;
        }
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        LOCK.writeLock().lock();
        File backFile = new File(EngineParameters.CodeIdentifyDataPath + "_" + object.hashCode() + "_" + dataFormat.format(new Date()));
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(EngineParameters.CodeIdentifyDataPath));
             ObjectOutputStream objectOutputStreamBack = new ObjectOutputStream(new FileOutputStream(backFile))) {
            objectOutputStream.writeObject(object);
            objectOutputStreamBack.writeObject(object);
            objectOutputStream.close();
            objectOutputStreamBack.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }finally {
            LOCK.writeLock().unlock();
        }

    }

    public static Object FileToObject(){
        if (new File(EngineParameters.CodeIdentifyDataPath).length() == 0) {
            return null;
        }
        Object object = null;
        LOCK.writeLock().lock();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(EngineParameters.CodeIdentifyDataPath))){
            object = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ClassNotFoundException ce){
            LOGGER.info("Try to get object from file, but failed");
        }finally {
            LOCK.writeLock().unlock();
        }
        return object;
    }

    public static void initPointMapWatcher(){
        ThreadSupport.threadPool.execute(() -> {
            while (StartService.SystemIsOnline){
                Object file = FileToObject();
                if ((file != null ? file.hashCode() : 0) != PointMap.getPointMap().hashCode()){
                    ObjectToFile(PointMap.getPointMap());
                    LOGGER.info("The new data has been flushed to file.");
                } else {
                    LOGGER.info("The same data didn't need to flush to file.");
                }
                TimeUtil.sleep(30 * 1000);
            }
        });
    }

}
