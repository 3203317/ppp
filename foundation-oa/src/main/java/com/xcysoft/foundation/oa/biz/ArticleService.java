package com.xcysoft.foundation.oa.biz;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.newcapec.framework.core.biz.BaseService;
import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.oa.model.Article;

/**
 *
 * @author huangxin
 *
 */
public interface ArticleService extends BaseService<Article> {

	@SuppressWarnings("rawtypes")
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby);
}
