package com.louie.authcode.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuhongyu.louie on 2016/9/10.
 */
public class ThreadSupport {

    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService threadPool = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS * 100);

}
