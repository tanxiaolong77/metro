package com.metro.service.base;

import java.io.Serializable;
import java.util.Map;

import org.springframework.dao.DataAccessException;
/**
 * @author badqiu
 */
public interface EntityDao <E,PK extends Serializable>{

	public E getById(PK id) throws DataAccessException;
	
	public int delete(PK id) throws DataAccessException;
	
	/** 插入数据 
	 * @throws  */
	public int insert(E entity) throws DataAccessException;
	
	/** 更新数据 */
	public int update(E entity) throws DataAccessException;

	public E selectOne(Map map) throws DataAccessException;
}
