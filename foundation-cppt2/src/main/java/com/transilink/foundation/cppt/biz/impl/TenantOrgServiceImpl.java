package com.transilink.foundation.cppt.biz.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transilink.foundation.cppt.biz.TenantOrgService;
import com.transilink.foundation.cppt.dao.TenantOrgDao;
import com.transilink.foundation.cppt.model.TenantOrg;
import com.transilink.framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
@Service("tenantOrgService")
@Transactional
@SuppressWarnings("all")
public class TenantOrgServiceImpl implements TenantOrgService {

	@Autowired
	private TenantOrgDao tenantOrgDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public TenantOrg get(String id) {
		return tenantOrgDao.get(id);
	}

	@Override
	public void removeUnused(String id) {
		tenantOrgDao.delete(id);
	}

	@Override
	public void saveOrUpdate(TenantOrg entity) {
		tenantOrgDao.saveOrUpdate(entity);
	}

	@Override
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		Page page = tenantOrgDao.findAll(paramMap, orderby);
		return page;
	}
}
