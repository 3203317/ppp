package com.xcysoft.foundation.cppt.biz.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.cppt.biz.MenuService;
import com.xcysoft.foundation.cppt.dao.MenuDao;
import com.xcysoft.foundation.cppt.model.Menu;

/**
 *
 * @author huangxin
 *
 */
@Service("menuService")
@Transactional
@SuppressWarnings("all")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Menu get(String id) {
		return menuDao.get(id);
	}

	@Override
	public void removeUnused(String id) {
		menuDao.delete(id);
	}

	@Override
	public void saveOrUpdate(Menu entity) {
		menuDao.saveOrUpdate(entity);
	}

	@Override
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		Page page = menuDao.findAll(paramMap, orderby);
		return page;
	}

	@Override
	public Page findChildren(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		Page page = menuDao.findAllChildren(paramMap, orderby);
		return page;
	}
}
