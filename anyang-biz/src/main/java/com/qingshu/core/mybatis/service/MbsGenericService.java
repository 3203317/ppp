package com.qingshu.core.mybatis.service;

import java.util.List;
import java.util.Map;

import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.pager.PagerInfo;

/**
 * 父类接口
 * 
 * @author zyf
 * @param <T>
 */
public interface MbsGenericService {
	/**
	 * 根据MAP查询单一对象
	 * 
	 * @param <T>
	 * @param pramMap
	 * @return
	 */
	public <T> T selectOne(String s, Map<String, Object> pramMap);
	/**
	 * 根据id查询单一对象
	 * 
	 * @param <T>
	 * @param pramMap
	 * @return
	 */
	public <T> T selectOne(String s, String id);
	/**
	 *查询list对象
	 */
	public <T> List<T> selectList(String s);
	/**
	 * 根据PagerInfo查询分页list对象
	 */
	public <T> T selectOne(String s, PagerInfo pagerInfo);
	/**
	 * 根据PagerInfo查询分页list对象
	 */
	public <T> List<T> selectList(String s, PagerInfo pagerInfo);
	/**
	 * 查询list对象
	 */
	public <T> List<T> selectList(String s, Object object);
	/**
	 * 查询list对象
	 */
	public <T> List<T> selectList(String s, Map<String, Object> pramMap);
	/**
	 * 保存对象
	 */
	public <T> AjaxJson insert(String s, T t);
	/**
	 * 更新对象
	 */
	public <T> AjaxJson update(String s, T t);
	/**
	 * 更新对象
	 */
	public <T> AjaxJson updateNopar(String s);
	/**
	 * 删除对象
	 */
	public <T> AjaxJson delete(String s, T t);
	/**
	 * 删除对象
	 */
	public <T> AjaxJson deleteNopar(String s);
	/**
	 * 多参数删除对象
	 */
	public <T> AjaxJson deleteMorePar(String s, Object...objects);
	/**
	 * 批量插入
	 */
	public <T> AjaxJson batchInsert(String s,List<T> t);
}
