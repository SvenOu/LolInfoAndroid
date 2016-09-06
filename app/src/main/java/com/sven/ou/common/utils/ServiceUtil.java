package com.sven.ou.common.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

public class ServiceUtil {
    private static final String TAG = ServiceUtil.class.getSimpleName();

    public static String generateId() {
        String uniqueID = UUID.randomUUID().toString();
        long timeStamp = new Date().getTime();
        return getMD5Str(uniqueID + timeStamp);
    }

    private static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "NoSuchAlgorithmException caught!");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        Log.d(TAG, md5StrBuff.toString().toLowerCase());
        return md5StrBuff.toString().toLowerCase();
    }
}
