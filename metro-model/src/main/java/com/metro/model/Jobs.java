package com.metro.model;

import java.util.Date;

public class Jobs {
    private String id;

    private String jobsName;

    private String isShow;

    private String openMatch;

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

    public String getJobsName() {
        return jobsName;
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName == null ? null : jobsName.trim();
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    public String getOpenMatch() {
        return openMatch;
    }

    public void setOpenMatch(String openMatch) {
        this.openMatch = openMatch == null ? null : openMatch.trim();
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