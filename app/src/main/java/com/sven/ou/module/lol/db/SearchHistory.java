package com.sven.ou.module.lol.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "search_history")
public class SearchHistory extends Model {

    public static final String HISTORY_TYPE_VIDEO = "video";
    public static final String HISTORY_TYPE_AUTHOR = "author";

    @Column(name = "history_id", unique = true)
    private String historyId;

    @Column(name = "type")
    private String type;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private Date createDate;

    // Make sure to have a default constructor for every ActiveAndroid model
    public SearchHistory(){
        super();
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}