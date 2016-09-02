package com.sven.ou.module.lol.entity.system;

public class BaseMenuData {

    private String key;
    private Class targetClass;

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

