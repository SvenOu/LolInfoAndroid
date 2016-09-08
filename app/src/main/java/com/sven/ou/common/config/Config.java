package com.sven.ou.common.config;

/**
 * Created by sven-ou on 2016/7/27.
 */
public class Config {

    /**
     * 测试模式
     */
    private static final String TEST_MODE = "TEST";
    /**
     * 正式版发布
     */
    private static final String PROD_MODE = "PROD";

    /**
     * change to "PROD" if need to publish the app.
     */
    private static final String CURRENT_MODE = TEST_MODE;

    /**
     * 带玩英雄联盟服务器 数据Api
     */
    private static final String DAIWAN_LOL_DATA_URL = "http://lolapi.games-cube.com";

    /**
     * 带玩英雄联盟服务器 视频Api
     */
    private static final String DAIWAN_LOL_VIDEO_URL = "http://infoapi.games-cube.com";

    /**
     *公共版战绩令牌
     */
    public static final String PUBLICK_LOL_REQUEST_TOKEN = "8A639-D8A99-694EE-6DA4E";

    /**
     * 视频令牌
     */
    public static final String VIDEO_REQUEST_TOKEN = "8BBC8-B8A86-CAAAA-5B454";

//    /**
//     * 触手战绩令牌（云API）
//     */
//    public static final String TENTACLE_LOL_REQUEST_TOKEN = "HJMKI-WXSZM-YEXCB-MFNDO";


    public static String getDaiWanLolDataUrl(){
        return DAIWAN_LOL_DATA_URL;
    }
    public static String getDaiWanLolVideoUrl(){
        return DAIWAN_LOL_VIDEO_URL;
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
