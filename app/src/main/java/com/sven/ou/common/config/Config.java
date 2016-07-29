package com.sven.ou.common.config;

/**
 * Created by sven-ou on 2016/7/27.
 */
public class Config {


    /**
     * 带玩英雄联盟服务器
     */
    private static final String DAIWAN_LOL_BASE_URL = "http://lolapi.games-cube.com";

    /**
     *公共版战绩令牌
     */
    public static final String PUBLICK_LOL_REQUEST_TOKEN = "LMHKO-CQKLZ-JJXBU-UPZRG";

    /**
     * 触手战绩令牌
     */
    public static final String TENTACLE_LOL_REQUEST_TOKEN = "LGURZ-QZGAS-WOGAV-XSXMX";
    /**
     * 视频令牌
     */
    public static final String VIDEO_REQUEST_TOKEN = "MFJBS-BSSMN-ANRFY-HZHDN";


    public static String getDaiWanLolBaseUrl(){
        return DAIWAN_LOL_BASE_URL;
    }

    public static boolean isDevelopMode(){
        return true;
    }
}
