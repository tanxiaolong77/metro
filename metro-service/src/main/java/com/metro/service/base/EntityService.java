package com.metro.service.base;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;
/**
 * @author 
 */
public interface EntityService <E,PK extends Serializable>{

	public E getById(PK id) throws DataAccessException;
	
	/** 插入数据 */
	public E save(E entity) throws DataAccessException;
	
	/** 更新数据 */
	public void update(E entity) throws DataAccessException;
	
	
	
}
