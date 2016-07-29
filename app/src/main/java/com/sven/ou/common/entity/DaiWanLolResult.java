package com.sven.ou.common.entity;

public class DaiWanLolResult<T> {
    private T data ;

    private int code;

    private String msg;

    public void setData(T data){
        this.data = data;
    }
    public T getData(){
        return this.data;
    }
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
}
