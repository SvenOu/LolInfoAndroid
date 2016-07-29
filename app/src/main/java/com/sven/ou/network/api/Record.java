package com.sven.ou.network.api;

import com.sven.ou.common.entity.LolResult;

/**
 * Created by sven-ou on 2016/7/27.
 */
public class Record extends LolResult{
    private String Record;

    public void setRecord(String Record){
        this.Record = Record;
    }
    public String getRecord(){
        return this.Record;
    }

    @Override
    public String toString() {
        return "Record{" +
                "Record='" + Record + '\'' +
                '}';
    }
}
