package com.xcysoft.foundation.oa.biz.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.oa.biz.ArticleService;
import com.xcysoft.foundation.oa.dao.ArticleDao;
import com.xcysoft.foundation.oa.model.Article;

/**
 *
 * @author huangxin
 *
 */
@Service("articleService")
@Transactional
@SuppressWarnings("all")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Article get(String id) {
		return articleDao.get(id);
	}

	@Override
	public void removeUnused(String id) {
		articleDao.delete(id);
	}

	@Override
	public void saveOrUpdate(Article entity) {
		articleDao.saveOrUpdate(entity);
	}

	@Override
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		Page page = articleDao.findAll(paramMap, orderby);
		return page;
	}

}