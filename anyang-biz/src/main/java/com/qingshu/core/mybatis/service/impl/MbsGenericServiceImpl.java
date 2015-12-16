package com.qingshu.core.mybatis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.plugin.log.LogAnnotation;
import com.qingshu.core.mybatis.dao.MbsCommonDao;
import com.qingshu.core.mybatis.service.MbsGenericService;

public class MbsGenericServiceImpl implements MbsGenericService {
	@Autowired
	public MbsCommonDao mbsCommonDao;
	public <T> T selectOne(String s, Map<String, Object> pramMap) {
		return mbsCommonDao.selectOne(s, pramMap);
	}
	public <T> T selectOne(String s, String id)
	{
		return mbsCommonDao.selectOne(s, id);
	}
	public <T> List<T> selectList(String s)
	{
		return mbsCommonDao.selectList(s);
	}
	public <T> List<T> selectList(String s, PagerInfo pagerInfo) {
		return mbsCommonDao.selectList(s, pagerInfo);
	}
	public <T> List<T> selectList(String s,Object object)
	{
		return mbsCommonDao.selectList(s, object);
	}
	public <T> List<T> selectList(String s, Map<String, Object> pramMap)
	{
		return mbsCommonDao.selectList(s, pramMap);
	}
	@LogAnnotation(logDesc="添加")
	public <T> AjaxJson insert(String s, T t) {
		
		return mbsCommonDao.insert(s, t);
	}
	/**
	 * 多参数删除对象
	 */
	@LogAnnotation(logDesc="删除")
	public <T> AjaxJson deleteMorePar(String s, Object...objects)
	{
		return mbsCommonDao.delete(s, objects);
	}
	/**
	 * 删除对象
	 */
	public <T> AjaxJson deleteNopar(String s)
	{
		return mbsCommonDao.delete(s);
	}
	@LogAnnotation(logDesc="更新")
	public <T> AjaxJson update(String s, T t) {
		
		return mbsCommonDao.update(s, t);
	}
	public <T> AjaxJson updateNopar(String s)
	{
		return mbsCommonDao.update(s);
	}
	@LogAnnotation(logDesc="删除")
	public <T> AjaxJson delete(String s, T t)
	{
		return mbsCommonDao.delete(s, t);
		
	}
	@LogAnnotation(logDesc="添加")
	public <T> AjaxJson batchInsert(String s,List<T> t) {
		
		return mbsCommonDao.batchInsert(s, t);
	}
	/**
	 * 根据PagerInfo查询分页list对象
	 */
	public <T> T selectOne(String s, PagerInfo pagerInfo)
	{
		return mbsCommonDao.selectOne(s,pagerInfo);
	}
	
}
