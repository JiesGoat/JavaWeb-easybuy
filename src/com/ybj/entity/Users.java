package com.ybj.entity;


import java.util.Date;

/**
 * 用户实体类
 */
public class Users {
    private String uid;//用户编号
    private String uname;//用户名
    private String upassword;//密码
    private String sex;//性别
    private Date birthday;//出生日期
    private String ucode;//身份证号
    private String email;//邮箱
    private String moblile;//手机号
    private String address;//住址
    private int ustatus;//用户状态(1代表为管理员)

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMoblile(String moblile) {
        this.moblile = moblile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUstatus(int ustatus) {
        this.ustatus = ustatus;
    }

    public String getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getUcode() {
        return ucode;
    }

    public String getEmail() {
        return email;
    }

    public String getMoblile() {
        return moblile;
    }

    public String getAddress() {
        return address;
    }

    public int getUstatus() {
        return ustatus;
    }
}
