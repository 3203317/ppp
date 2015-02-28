package com.xcysoft.foundation.oa.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.oa.dao.base.BaseArticleDao;

/**
 *
 * @author huangxin
 *
 */
@Repository
@SuppressWarnings("all")
public class ArticleDao extends BaseArticleDao {

	public Page findAll(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(
				"select t.* from oa_article t where 1=1");
		if (null != paramMap) {
			if (paramMap.containsKey("article_title")
					&& StringUtils.isNotBlank(paramMap.get("article_title")
							.toString())) {
				sql.append(" and t.article_title like ?");
				param.add("%" + paramMap.get("article_title") + "%");
			}
		}
		return this.sqlQueryForPage(sql.toString(), param.toArray(), orderby);
	}
}
