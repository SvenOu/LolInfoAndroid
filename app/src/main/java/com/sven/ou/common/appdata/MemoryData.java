package com.sven.ou.common.appdata;

import com.sven.ou.module.lol.entity.UserArea;

/**
 * 全局内存数据
 */
public class MemoryData {
    private static final String TAG = MemoryData.class.getSimpleName();

    private UserArea curSelectUserArea;

    public UserArea getCurSelectUserArea() {
        return curSelectUserArea;
    }

    public void setCurSelectUserArea(UserArea curSelectUserArea) {
        this.curSelectUserArea = curSelectUserArea;
    }
}
