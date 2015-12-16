package com.qingshu.core.mybatis.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.ReflectHelper;
import com.qingshu.core.mybatis.dao.MbsGenericDao;
@Repository
public class MbsGenericDaoImpl implements MbsGenericDao {

	@Resource
	public SqlSessionTemplate sqlSession;
	public AjaxJson j;

	/**
	 * 
	 * 获取单一对象
	 */
	public <T> T selectOne(String s, Map<String, Object> pramMap) {
		return sqlSession.selectOne(s, pramMap);
	}

	/**
	 * 
	 * 根据主键获取单一对象
	 */
	public <T> T selectOne(String s, String id) {
		return sqlSession.selectOne(s, id);
	}
	/**
	 * 根据PagerInfo查询分页list对象
	 */
	public <T> T selectOne(String s, PagerInfo pagerInfo)
	{
		return sqlSession.selectOne(s,pagerInfo);
	}

	/**
	 * 获取分页List对象
	 */
	public <T> List<T> selectList(String s, PagerInfo pagerInfo) {
		return sqlSession.selectList(s, pagerInfo);
	}

	/**
	 * 获取List对象
	 */
	public <T> List<T> selectList(String s, Object object) {
		return sqlSession.selectList(s, object);
	}

	/**
	 * 获取List对象
	 */
	public <T> List<T> selectList(String s) {
		return sqlSession.selectList(s);
	}

	/**
	 * 获取List对象
	 */
	public <T> List<T> selectList(String s, Map<String, Object> map) {
		return sqlSession.selectList(s, map);
	}
	public <T> AjaxJson delete(String s)
	{
		j = new AjaxJson();
		if (!ObjectUtils.isEmpty(s)) {
			try {
				int n = sqlSession.delete(s);
				j.setInfo("成功删除" + n + "条记录");
			} catch (Exception e) {
				j.setInfo("数据:删除失败,<br>失败原因:" + e.getCause().getCause().getMessage(), false, "alert");

			}
		} else {
			j.setInfo("删除失败：所选对象不可删除", false, "alert");
		}
		return j;
	}
	/**
	 * 删除对象
	 */
	public <T> AjaxJson delete(String s, T t) {
		j = new AjaxJson();
		if (!ObjectUtils.isEmpty(t)) {
			try {
				int n = sqlSession.delete(s, t);
				j.setInfo("成功删除" + n + "条记录");
			} catch (Exception e) {
				j.setInfo("数据:" + t + "删除失败,<br>失败原因:" + e.getCause().getCause().getMessage(), false, "alert");

			}
		} else {
			j.setInfo("删除失败：所选对象不可删除", false, "alert");
		}
		return j;
	}
	/**
	 * 删除对象
	 */
	public <T> AjaxJson delete(String s,Object...objects) {
		j = new AjaxJson();
		if (!ObjectUtils.isEmpty(objects)) {
			try {
				int n = sqlSession.delete(s, objects);
				j.setInfo("成功删除" + n + "条记录");
			} catch (Exception e) {
				j.setInfo("数据:" + objects + "删除失败,<br>失败原因:" + e.getCause().getCause().getMessage(), false, "alert");

			}
		} else {
			j.setInfo("删除失败：所选对象不可删除", false, "alert");
		}
		return j;
	}


	/**
	 * 保存对象
	 */
	public <T> AjaxJson insert(String s, T t) {
		j = new AjaxJson();
		if (!ObjectUtils.isEmpty(t)) {
			ReflectHelper reflectHelper = new ReflectHelper(t);
			Object id = reflectHelper.getMethodValue("id");
			if (ObjectUtils.isEmpty(id)) {
				id = ObjectUtils.getUUID();
				reflectHelper.setMethodValue("id", id);
			}
			try {
				sqlSession.insert(s, t);
				j.setInfo("保存成功");
				Map<String, Object> map = ObjectUtils.getHashMap();
				map.put("id", id.toString());
				j.setObject(t);
				j.setAttributes(map);
			} catch (Exception e) {
				j.setInfo("数据:" + t + "保存失败,<br>失败原因:" + e.getCause().getCause().getMessage(), false, "alert");
			}

		} else {
			j.setInfo("保存失败：对象为空", false, "alert");
		}
		return j;

	}

	/**
	 * 更新对象
	 */
	public <T> AjaxJson update(String s, T t) {
		j = new AjaxJson();
		if (!ObjectUtils.isEmpty(t)) {
			try {
				sqlSession.update(s, t);
				j.setInfo("更新成功");
			} catch (Exception e) {
				j.setInfo("数据:" + t + "更新失败,<br>失败原因:" + e.getCause().getCause().getMessage(), false, "alert");
			}

		} else {
			j.setInfo("更新失败：对象为空", false, "alert");
		}
		return j;
	}
	/**
	 * 更新对象
	 */
	public <T> AjaxJson update(String s) {
		j = new AjaxJson();
		if (!ObjectUtils.isEmpty(s)) {
			try {
				int i=sqlSession.update(s);
				j.setInfo("成功更新"+i+"条数据");
			} catch (Exception e) {
				j.setInfo("更新失败,<br>失败原因:" + e.getCause().getCause().getMessage(), false, "alert");
			}

		} else {
			j.setInfo("更新失败：对象为空", false, "alert");
		}
		return j;
	}
	

	/**
	 * 批量保存对象
	 */
	public <T> AjaxJson batchInsert(String s, List<T> t) {
		j = new AjaxJson();
		List<T> temp = new ArrayList<T>();
		for (T t2 : t) {
			ReflectHelper reflectHelper = new ReflectHelper(t2);
			reflectHelper.setMethodValue("id", ObjectUtils.getUUID());
			temp.add(t2);
		}
		int n = sqlSession.insert(s, temp);
		if (n > 0) {
			j.setInfo("成功批量插入" + n + "条件记录");
			return j;
		} else {
			return j;
		}
	}
}
