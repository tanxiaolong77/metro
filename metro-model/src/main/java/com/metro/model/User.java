package com.metro.model;

import java.util.Date;

public class User {
    private String id;

    private String userName;

    private String passWord;

    private String role;

    private Date createTime;

    private String operater;

    private Date updateTime;

    private String modifier;

    private String verbFiled1;

    private String verbFiled2;

    private String verbFiled3;

    private String verbFiled4;

    private String verbFiled5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater == null ? null : operater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getVerbFiled1() {
        return verbFiled1;
    }

    public void setVerbFiled1(String verbFiled1) {
        this.verbFiled1 = verbFiled1 == null ? null : verbFiled1.trim();
    }

    public String getVerbFiled2() {
        return verbFiled2;
    }

    public void setVerbFiled2(String verbFiled2) {
        this.verbFiled2 = verbFiled2 == null ? null : verbFiled2.trim();
    }

    public String getVerbFiled3() {
        return verbFiled3;
    }

    public void setVerbFiled3(String verbFiled3) {
        this.verbFiled3 = verbFiled3 == null ? null : verbFiled3.trim();
    }

    public String getVerbFiled4() {
        return verbFiled4;
    }

    public void setVerbFiled4(String verbFiled4) {
        this.verbFiled4 = verbFiled4 == null ? null : verbFiled4.trim();
    }

    public String getVerbFiled5() {
        return verbFiled5;
    }

    public void setVerbFiled5(String verbFiled5) {
        this.verbFiled5 = verbFiled5 == null ? null : verbFiled5.trim();
    }
}