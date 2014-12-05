package framework.core.biz;

import framework.core.logs.LogEnabled;

/**
 *
 * @author huangxin
 */
public interface BaseService<T> extends LogEnabled {
	/***
	 * 删除一个实体 参数 实体主键
	 *
	 * @param id
	 */
	public void removeUnused(String id);

	/**
	 * 通过id 得到一个实体
	 *
	 * @param id
	 * @return
	 */
	public T get(String id);

	/***
	 * 保存或更新
	 *
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

}
