package com.sven.ou.common.config;

import com.sven.ou.BuildConfig;
import com.sven.ou.module.launch.view.AppLaunchFragment;

/**
 * Created by sven-ou on 2016/7/27.
 */
public class Config {

    /**
     * 测试模式
     */
    private static final String TEST_MODE = BuildConfig.TEST_MODE;
    /**
     * 正式版发布
     */
    private static final String PROD_MODE = BuildConfig.PROD_MODE;

    /**
     * change to "PROD" if need to publish the app.
     */
    private static final String CURRENT_MODE = BuildConfig.CURRENT_MODE;

    /**
     * 带玩服务器
     */
    private static final String DAIWAN_LOL_HACK_URL = "http://www.games-cube.com";
    /**
     * 带玩英雄联盟服务器 数据Api
     */
    private static final String DAIWAN_LOL_DATA_URL = "http://lolapi.games-cube.com";

    /**
     * 带玩英雄联盟服务器 视频Api
     */
    private static final String DAIWAN_LOL_VIDEO_URL = "http://infoapi.games-cube.com";

    /**
     *公共版战绩令牌,视频令牌,触手战绩令牌（云API）
     * set in {@link AppLaunchFragment#initDaiWanToken()}
     */
    public static String PUBLICK_LOL_REQUEST_TOKEN;
    public static String VIDEO_REQUEST_TOKEN;
    public static String TENTACLE_LOL_REQUEST_TOKEN;


    public static String getDaiWanLolDataUrl(){
        return DAIWAN_LOL_DATA_URL;
    }
    public static String getDaiWanLolVideoUrl(){
        return DAIWAN_LOL_VIDEO_URL;
    }
    public static String getDaiWanLolHackUrl(){
        return DAIWAN_LOL_HACK_URL;
    }

    public static boolean isDevelopMode(){
        return true;
    }

    public static String getDataBasePrefix(){
        String testPrefix = "test_";
        String proPrefix = "pro_";

        if (CURRENT_MODE.equalsIgnoreCase(PROD_MODE))
            return proPrefix;
        else if (CURRENT_MODE.equalsIgnoreCase(TEST_MODE))
            return testPrefix;
        else
            return testPrefix;
    }
}
