package com.sven.ou.module.lol.entity;

import java.io.Serializable;

/**
 * Created by sven-ou on 2016/8/5.
 */
public class Video{
    /**
     * 文章唯一ID
     */
    private String guid;

    private String vid;

    /**
     * 标题
     */
    private String title;

    private String bigimg;

    /**
     * 文章小图
     */
    private String img;

    private String url;

    private String physicalpath;

    /**
     * 视频路径
     */
    private String virtualpath;

    /**
     * iframe
     */
    private String content;

    private String tag;

    /**
     * 视频创建时间
     */
    private String createdate;

    private String catalog;

    /**
     * 作者
     */
    private Author author;

    private String source;

    private String comments;

    private boolean headlines;

    private boolean exestatus;

    private boolean writefilestatus;

    private String uuid;

    private String type;

    private String hero;

    private String play;

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVid() {
        return this.vid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setBigimg(String bigimg) {
        this.bigimg = bigimg;
    }

    public String getBigimg() {
        return this.bigimg;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return this.img;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setPhysicalpath(String physicalpath) {
        this.physicalpath = physicalpath;
    }

    public String getPhysicalpath() {
        return this.physicalpath;
    }

    public void setVirtualpath(String virtualpath) {
        this.virtualpath = virtualpath;
    }

    public String getVirtualpath() {
        return this.virtualpath;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCreatedate() {
        return this.createdate;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return this.comments;
    }

    public void setHeadlines(boolean headlines) {
        this.headlines = headlines;
    }

    public boolean getHeadlines() {
        return this.headlines;
    }

    public void setExestatus(boolean exestatus) {
        this.exestatus = exestatus;
    }

    public boolean getExestatus() {
        return this.exestatus;
    }

    public void setWritefilestatus(boolean writefilestatus) {
        this.writefilestatus = writefilestatus;
    }

    public boolean getWritefilestatus() {
        return this.writefilestatus;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public String getHero() {
        return this.hero;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getPlay() {
        return this.play;
    }

    @Override
    public String toString() {
        return "Video{" +
                "guid='" + guid + '\'' +
                ", vid='" + vid + '\'' +
                ", title='" + title + '\'' +
                ", bigimg='" + bigimg + '\'' +
                ", img='" + img + '\'' +
                ", url='" + url + '\'' +
                ", physicalpath='" + physicalpath + '\'' +
                ", virtualpath='" + virtualpath + '\'' +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                ", createdate='" + createdate + '\'' +
                ", catalog='" + catalog + '\'' +
                ", video=" + author +
                ", source='" + source + '\'' +
                ", comments='" + comments + '\'' +
                ", headlines=" + headlines +
                ", exestatus=" + exestatus +
                ", writefilestatus=" + writefilestatus +
                ", uuid='" + uuid + '\'' +
                ", type='" + type + '\'' +
                ", hero='" + hero + '\'' +
                ", play='" + play + '\'' +
                '}';
    }
}
