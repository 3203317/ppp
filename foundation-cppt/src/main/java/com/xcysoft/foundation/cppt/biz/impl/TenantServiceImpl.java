package com.xcysoft.foundation.cppt.biz.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.cppt.biz.TenantService;
import com.xcysoft.foundation.cppt.dao.TenantDao;
import com.xcysoft.foundation.cppt.model.Tenant;

/**
 *
 * @author huangxin
 *
 */
@Service("tenantService")
@Transactional
@SuppressWarnings("all")
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantDao tenantDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Tenant get(String id) {
		return tenantDao.get(id);
	}

	@Override
	public void removeUnused(String id) {
		tenantDao.delete(id);
	}

	@Override
	public void saveOrUpdate(Tenant entity) {
		tenantDao.saveOrUpdate(entity);
	}

	@Override
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		Page page = tenantDao.findAll(paramMap, orderby);
		return page;
	}
}
