package com.sven.ou.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 分页
 */
public class Page<T> {
    /**
     * 当前页
     */
    private int curPage;

    /**
     * 总页数
     */
    private int totalPage;

    private int hId;

    @SerializedName(value = "data", alternate = {"skillRank"})//为data字段提供备选属性名
    private T data;

    private int pageSize;

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getCurPage() {
        return this.curPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setHId(int hId) {
        this.hId = hId;
    }

    public int getHId() {
        return this.hId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return this.pageSize;
    }
}
