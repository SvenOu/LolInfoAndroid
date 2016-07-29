package com.sven.ou.common.utils;

import android.util.Log;

import com.sven.ou.common.config.Config;

/**
 * Created by sven-ou on 2016/7/29.
 */
public final class Logger {
    private Logger() {}

    /**
     * Send a {@link android.util.Log#VERBOSE} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int v(String tag, String msg) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.v(tag, msg);
    }

    /**
     * Send a {@link android.util.Log#VERBOSE} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int v(String tag, String msg, Throwable tr) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.v(tag, msg, tr);
    }

    /**
     * Send a {@link android.util.Log#DEBUG} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int d(String tag, String msg) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.d(tag, msg);
    }

    /**
     * Send a {@link android.util.Log#DEBUG} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int d(String tag, String msg, Throwable tr) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.d(tag, msg, tr);
    }

    /**
     * Send an {@link android.util.Log#INFO} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int i(String tag, String msg) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.i(tag, msg);
    }

    /**
     * Send a {@link android.util.Log#INFO} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int i(String tag, String msg, Throwable tr) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.i(tag,msg, tr);
    }

    /**
     * Send a {@link android.util.Log#WARN} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int w(String tag, String msg) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.w(tag, msg);
    }

    /**
     * Send a {@link android.util.Log#WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int w(String tag, String msg, Throwable tr) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.w(tag, msg, tr);
    }

    /*
     * Send a {@link android.util.Log#WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    public static int w(String tag, Throwable tr) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.w(tag,tr);
    }

    /**
     * Send an {@link android.util.Log#ERROR} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int e(String tag, String msg) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.e(tag, msg);
    }

    /**
     * Send a {@link android.util.Log#ERROR} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int e(String tag, String msg, Throwable tr) {
        if(!cantPrintLog()){
            return -1;
        }
        return Log.e(tag, msg, tr);
    }

    private static boolean cantPrintLog() {
        return Config.isDevelopMode();
    }
}
