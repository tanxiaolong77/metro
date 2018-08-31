package com.metro.vo;

import java.io.Serializable;

/***
 * 相应前端实体
 * @author dell
 *
 */
public class DataTransObj implements Serializable {

	private static final long serialVersionUID = 930206810116830404L;

	/**
	 * true:成功
	 * false:失败
	 */
	private boolean rstFlag;

	/**
	 * 响应数据
	 * 根据具体业务完善服务接口文档
	 */
    private Object rstObj;

    /**
     * 响应描述
     */
    public String rstDesc;

    /**
     * 失败
     * @param rstObj
     * @param rstDesc
     * @return
     */
    public static DataTransObj onFailure(Object rstObj, String rstDesc) {
        DataTransObj dataTransObj = new DataTransObj();
        dataTransObj.setRstDesc(rstDesc);
        dataTransObj.setRstFlag(false);
        dataTransObj.setRstObj(rstObj);
        return dataTransObj;
    }

    /**
     * 成功
     * @param rstObj
     * @param rstDesc
     * @return
     */
    public static DataTransObj onSuccess(Object rstObj, String rstDesc) {
        DataTransObj dataTransObj = new DataTransObj();
        dataTransObj.setRstDesc(rstDesc);
        dataTransObj.setRstFlag(true);
        dataTransObj.setRstObj(rstObj);
        return dataTransObj;
    }

	public boolean isRstFlag() {
		return rstFlag;
	}

	public void setRstFlag(boolean rstFlag) {
		this.rstFlag = rstFlag;
	}

	public Object getRstObj() {
		return rstObj;
	}

	public void setRstObj(Object rstObj) {
		this.rstObj = rstObj;
	}

	public String getRstDesc() {
		return rstDesc;
	}

	public void setRstDesc(String rstDesc) {
		this.rstDesc = rstDesc;
	}
}
