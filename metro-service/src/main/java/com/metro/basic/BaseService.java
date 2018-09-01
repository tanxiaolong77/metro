package com.metro.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.metro.model.BaseExample;

/***
 * 更新版本
 * ClassName:BaseService.java
 * @author Sam Tan
 * @Description TODO
 * @date 2018年7月18日
 */
@Transactional
public abstract class BaseService<T,PK> {
 
	protected abstract EntityDao<T,PK> getEntityDao();

	@Transactional(readOnly = true)
	public T getById(PK id) throws DataAccessException {
		return (T) getEntityDao().selectByPrimaryKey(id);
	}

	public int insert(T entity) {
		return getEntityDao().insertSelective(entity);
	}

	public int update(T entity) throws DataAccessException {
		return getEntityDao().updateByPrimaryKeySelective(entity);
	}
	
    public List<T> selectByExample(BaseExample example) throws DataAccessException {
    	return getEntityDao().selectByExample(example);
    }
    
    public int countByExample(BaseExample example) throws DataAccessException {
    	return getEntityDao().countByExample(example).intValue();
    }
    
    public int deleteById(PK id) throws DataAccessException {
    	return getEntityDao().deleteByPrimaryKey(id);
    }
    
    public int updateByExample(T entity , BaseExample example) {
    	return getEntityDao().updateByExampleSelective(entity,example);
    }
    
    public int deleteByExample(BaseExample example) throws DataAccessException{
    	return getEntityDao().deleteByExample(example);
    }
}
