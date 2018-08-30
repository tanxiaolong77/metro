package com.metro.model;

/***
 * 查询条件抽象
 * ClassName:BaseExample.java
 * @author Sam Tan
 * @Description TODO
 * @date 2018年7月17日
 */
public class BaseExample {

    protected Integer limitStart = 0;

    protected Integer limitEnd = 50;

    public Integer getLimitStart() {
		return limitStart;
	}

	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}

	public Integer getLimitEnd() {
		return limitEnd;
	}

	public void setLimitEnd(Integer limitEnd) {
		this.limitEnd = limitEnd;
	}
	
}
