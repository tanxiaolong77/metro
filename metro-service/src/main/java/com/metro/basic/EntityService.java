package com.metro.basic;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.metro.model.BaseExample;

/***
 * common use
 * ClassName:EntityService.java
 * @author Sam Tan
 * @Description TODO
 * @date 2017年11月21日
 */
public interface EntityService<T,PK> {

	public T getById(PK id) throws DataAccessException;

	public int insert(T entity)throws DataAccessException;

	public int update(T entity) throws DataAccessException;
	
    public List<T> selectByExample(BaseExample example)throws DataAccessException;
    
    public int countByExample(BaseExample example)throws DataAccessException;
    
    public int deleteById(PK id) throws DataAccessException;
    
	public int updateByExample(T entity , BaseExample example) throws DataAccessException;;

    public int deleteByExample(BaseExample example) throws DataAccessException;
}