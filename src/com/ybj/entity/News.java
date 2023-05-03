package com.ybj.entity;

import java.sql.Timestamp;

/**
 * 新闻实体类
 */
public class News {
    private int nid;//新闻编号
    private String title;//新闻标题
    private String content;//新闻内容
    private Timestamp createtime;//发布时间

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public int getNid() {
        return nid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }
}
