package com.sven.ou.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * 通过序列化实现属性复制（依赖GSon）
 */
public class BeanUtils {

    public static String serializeObject(Object o) {
        Gson gson = buildGson();
        String serializedObject = gson.toJson(o);
        return serializedObject;
    }

    public static Object unserializeObject(String s, Class<?> targetClass) {
        Gson gson = buildGson();
        Object object = gson.fromJson(s, targetClass);
        return object;
    }

    private static Gson buildGson(){
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
        return builder.create();
    }

    public static Object cloneObject(Object o, Class<?> targetClass) {
        String s = serializeObject(o);
        Object object = unserializeObject(s, targetClass);
        return object;
    }
}
