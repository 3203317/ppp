package com.xcysoft.foundation.oa.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.newcapec.framework.core.handler.MultiViewResource;
import cn.newcapec.framework.core.utils.fileUtils.SysConfigUtil;
import cn.newcapec.framework.core.utils.pagesUtils.Page;
import cn.newcapec.framework.core.utils.pagesUtils.PageContext;
import cn.newcapec.framework.core.utils.pagesUtils.PageView;

import com.xcysoft.foundation.oa.biz.ArticleService;
import com.xcysoft.foundation.oa.model.Article;

/**
 *
 * @author huangxin
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/site")
@SuppressWarnings("all")
public class SiteController extends MultiViewResource {

	@Autowired
	private ArticleService articleService;

	private LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

	/**
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView indexUI(ModelMap modelMap) {
		modelMap.put("cdn", SysConfigUtil.get("html.cdn"));
		modelMap.put("virtualPath", SysConfigUtil.get("html.virtualPath"));

		JSONObject jsonObj = getJsonObject();
		if (null != jsonObj && jsonObj.containsKey("tenant")) {
			modelMap.put("tenant", jsonObj.get("tenant"));
		}

		if (null != jsonObj) {
			modelMap.put("urlparams", jsonObj.toString());
		}

		Article article = new Article();
		article.setArticle_title("haha");
		article.setId("3");

		articleService.saveOrUpdate(article);

		articleService.get("1");

		Page page = articleService.findList(null, null);
		PageView<Map<String, Object>> pageView = new PageView<Map<String, Object>>(
				PageContext.getPageSize(), PageContext.getOffset());
		pageView.setQueryResult(page);
		modelMap.put("pageView", pageView);

		return toView(getUrl("site.indexUI"), modelMap);
	}

}
