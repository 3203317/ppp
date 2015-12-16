package com.qingshu.core.hibernate.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingshu.core.hibernate.dao.HbmCommonDao;
import com.qingshu.core.hibernate.qbc.CriteriaQuery;
import com.qingshu.core.hibernate.service.HbmCommonService;

@Service
@Transactional
public class HbmCommonServiceImpl implements HbmCommonService {
	@Resource
	protected HbmCommonDao commonDao = null;
	public <T> T save(T entity) {
		return commonDao.save(entity);
	}
	public <T> T getEntity(Class<T> entityName, Serializable id)
	{
		return commonDao.getEntity(entityName, id);
	}
	public <T> T findByExample(T exampleEntity){
		return commonDao.findByExample( exampleEntity);
	}
	public <T> List<T> getPagerList(CriteriaQuery cq,Boolean isOffset)
	{
		return commonDao.getPagerList(cq, isOffset);
	}

}
