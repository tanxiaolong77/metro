package com.metro.basic;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.metro.model.BaseExample;

/***
 * ClassName:EntityDao.java
 * @author Sam Tan
 * @Description TODO
 * @date 2018年1月30日
 */
public interface EntityDao<T , PK> {

	public T selectByPrimaryKey(PK id) throws DataAccessException;

	public int insertSelective(T entity) throws DataAccessException;

	public int updateByPrimaryKeySelective(T entity) throws DataAccessException;
	
    public List<T> selectByExample(BaseExample example) throws DataAccessException;
    
    public int countByExample(BaseExample example) throws DataAccessException;
    
 
}
