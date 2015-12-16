package com.qingshu.core.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.pager.PagerInfo;

public interface MbsGenericDao {
	public <T> T selectOne(String s, Map<String, Object> pramMap);
	public <T> T selectOne(String s, String id);
	public <T> T selectOne(String s, PagerInfo pagerInfo);
	public <T> List<T> selectList(String s, Map<String,Object> map);
	public <T> List<T> selectList(String s, PagerInfo pagerInfo);
	public <T> List<T> selectList(String s);
	public <T> List<T> selectList(String s, Object object);
	public <T> AjaxJson insert(String s, T t);
	public <T> AjaxJson delete(String s, T t);
	/**
	 * 删除对象
	 */
	public <T> AjaxJson delete(String s,Object...objects);
	public <T> AjaxJson delete(String s);
	public <T> AjaxJson update(String s, T t);
	public <T> AjaxJson update(String s);
	public <T> AjaxJson batchInsert(String s,List<T> t);
}
