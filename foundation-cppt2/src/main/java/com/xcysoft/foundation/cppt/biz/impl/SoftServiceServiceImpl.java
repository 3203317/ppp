package com.xcysoft.foundation.cppt.biz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transilink.framework.core.utils.pagesUtils.Page;
import com.xcysoft.foundation.cppt.biz.SoftServiceService;
import com.xcysoft.foundation.cppt.dao.SoftServiceDao;
import com.xcysoft.foundation.cppt.model.SoftService;

/**
 *
 * @author huangxin
 *
 */
@Service("softServiceService")
@Transactional
@SuppressWarnings("all")
public class SoftServiceServiceImpl implements SoftServiceService {

	@Autowired
	private SoftServiceDao softServiceDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public SoftService get(String id) {
		return softServiceDao.get(id);
	}

	@Override
	public void removeUnused(String id) {
		softServiceDao.delete(id);
	}

	@Override
	public void saveOrUpdate(SoftService entity) {
		softServiceDao.saveOrUpdate(entity);
	}

	@Override
	public Page findList(Map<String, Object> paramMap) {
		Page page = softServiceDao.findAll(paramMap);
		return page;
	}
}
