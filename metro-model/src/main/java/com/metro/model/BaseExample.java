package com.metro.model;

/***
 * 查询条件抽象
 * ClassName:BaseExample.java
 * @author Sam Tan
 * @Description TODO
 * @date 2018年7月17日
 */
public class BaseExample {

    protected Integer startNumber = 0;

    protected Integer pageSize = 50;

	public Integer getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
