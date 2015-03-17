package com.transilink.foundation.cppt.biz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transilink.foundation.cppt.biz.ActionService;
import com.transilink.foundation.cppt.dao.ActionDao;
import com.transilink.foundation.cppt.model.Action;
import com.transilink.framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
@Service("actionService")
@Transactional
@SuppressWarnings("all")
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDao actionDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Action get(String id) {
		return actionDao.get(id);
	}

	@Override
	public void removeUnused(String id) {
		actionDao.delete(id);
	}

	@Override
	public void saveOrUpdate(Action entity) {
		actionDao.saveOrUpdate(entity);
	}

	@Override
	public Page findList(Map<String, Object> paramMap) {
		Page page = actionDao.findAll(paramMap);
		return page;
	}
}
