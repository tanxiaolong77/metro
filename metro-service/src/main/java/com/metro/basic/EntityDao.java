package com.metro.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.metro.model.BaseExample;

/***
 * mnt-service加强版
 * @author dell
 *
 * @param <T>
 * @param <PK>
 */
public interface EntityDao<T , PK> {

	public T selectByPrimaryKey(PK id) throws DataAccessException;

	public int insertSelective(T entity) throws DataAccessException;

	public int updateByPrimaryKeySelective(T entity) throws DataAccessException;
	
	int updateByExampleSelective(@Param("record") T entity , @Param("example") BaseExample example);
	
    public List<T> selectByExample(BaseExample example) throws DataAccessException;
    
    public int countByExample(BaseExample example) throws DataAccessException;
    
    public int deleteByPrimaryKey(PK id) throws DataAccessException;
    
    public int deleteByExample(BaseExample example) throws DataAccessException;
    
 
}
