package com.xcysoft.foundation.oa.biz.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcysoft.foundation.oa.biz.ArticleService;
import com.xcysoft.foundation.oa.model.Article;

/**
 *
 * @author huangxin
 *
 */
@Service("actionService")
@Transactional
@SuppressWarnings("all")
public class ArticleServiceImpl implements ArticleService {

	@Override
	public Article get(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUnused(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(Article arg0) {
		// TODO Auto-generated method stub

	}

}