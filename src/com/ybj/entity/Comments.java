package com.ybj.entity;

import java.sql.Timestamp;

/**
 * 商品评论实体类
 */
public class Comments {
    private int cid;//评论编号
    private String content;//评论内容
    private Timestamp createtime;//评论时间
    private int pid;//评论的商品编号
    private String nickname;//评论者的昵称

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

