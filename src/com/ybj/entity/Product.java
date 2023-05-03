package com.ybj.entity;

/**
 * 商品实体类
 */
public class Product {
    private int pid;//商品编号
    private String pname;//商品名称
    private String pdescription;//商品介绍
    private double price;//价格
    private int stock;//库存
    private int cid;//分类id
    private int childid;//子id
    private String filename;//图片文件名

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setChildid(int childid) {
        this.childid = childid;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public String getPdescription() {
        return pdescription;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getCid() {
        return cid;
    }

    public int getChildid() {
        return childid;
    }

    public String getFilename() {
        return filename;
    }
}
