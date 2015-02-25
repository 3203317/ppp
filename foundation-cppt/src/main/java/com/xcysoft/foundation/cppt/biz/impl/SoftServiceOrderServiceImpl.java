package com.xcysoft.foundation.cppt.biz.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.cppt.biz.SoftServiceOrderService;
import com.xcysoft.foundation.cppt.dao.SoftServiceOrderDao;
import com.xcysoft.foundation.cppt.model.SoftServiceOrder;

/**
 *
 * @author huangxin
 *
 */
@Service("softServiceOrderService")
@Transactional
@SuppressWarnings("all")
public class SoftServiceOrderServiceImpl implements SoftServiceOrderService {

	@Autowired
	private SoftServiceOrderDao softServiceOrderDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public SoftServiceOrder get(String id) {
		return softServiceOrderDao.get(id);
	}

	@Override
	public void removeUnused(String id) {
		softServiceOrderDao.delete(id);
	}

	@Override
	public void saveOrUpdate(SoftServiceOrder entity) {
		softServiceOrderDao.saveOrUpdate(entity);
	}

	@Override
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		Page page = softServiceOrderDao.findAll(paramMap, orderby);
		return page;
	}
}
