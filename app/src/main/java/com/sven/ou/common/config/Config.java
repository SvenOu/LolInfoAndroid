package com.sven.ou.common.config;

/**
 * Created by sven-ou on 2016/7/27.
 */
public class Config {


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
    public static final String PUBLICK_LOL_REQUEST_TOKEN = "JCQOH-WZJEH-IQAFK-FXKBP";

    /**
     * 视频令牌
     */
    public static final String VIDEO_REQUEST_TOKEN = "NANNZ-LMYSQ-ZVKHL-DCZVE";

    /**
     * 触手战绩令牌（云API）
     */
    public static final String TENTACLE_LOL_REQUEST_TOKEN = "MCOHY-CMBOY-DJVNA-BMKKF";


    public static String getDaiWanLolDataUrl(){
        return DAIWAN_LOL_DATA_URL;
    }
    public static String getDaiWanLolVideoUrl(){
        return DAIWAN_LOL_VIDEO_URL;
    }

    public static boolean isDevelopMode(){
        return true;
    }
}
