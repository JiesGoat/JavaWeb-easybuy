package com.ybj.entity;

/**
 * 商品分类实体类
 */
public class ProductCategory {
    private int pcid;//商品分类编号
    private String pcname;//商品分类名称
    private int parentid;//父级id

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public String getPcname() {
        return pcname;
    }

    public void setPcname(String pcname) {
        this.pcname = pcname;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }
}
