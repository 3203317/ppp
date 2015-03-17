package com.transilink.foundation.cppt.biz.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transilink.foundation.cppt.biz.TenantUserService;
import com.transilink.foundation.cppt.dao.TenantUserDao;
import com.transilink.foundation.cppt.model.TenantUser;
import com.transilink.framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
@Service("tenantUserService")
@Transactional
@SuppressWarnings("all")
public class TenantUserServiceImpl implements TenantUserService {

	@Autowired
	private TenantUserDao tenantUserDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public TenantUser get(String id) {
		return tenantUserDao.get(id);
	}

	@Override
	public void removeUnused(String id) {
		tenantUserDao.delete(id);
	}

	@Override
	public void saveOrUpdate(TenantUser entity) {
		tenantUserDao.saveOrUpdate(entity);
	}

	@Override
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		Page page = tenantUserDao.findAll(paramMap, orderby);
		return page;
	}
}
