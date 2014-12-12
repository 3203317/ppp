/**
 *
 */
package com.xcysoft.foundation.portal.dao.base;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.portal.model.Student;

/**
 * 学生操作基础类
 *
 * @author andy
 *
 */
@SuppressWarnings("all")
public abstract class BaseStudentDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return Student.class;
	}

	public Student cast(Object object) {
		return (Student) object;
	}

	public Student load(java.io.Serializable key) {
		return (Student) load(getReferenceClass(), key);
	}

	public Student get(java.io.Serializable key) {
		return (Student) get(getReferenceClass(), key);
	}

	public java.util.List findAll() {
		return find("from " + getReferenceClass().getName());
	}

	public void save(Student student) {
		super.save(student);
	}

	public void saveOrUpdate(Student student) {
		saveOrUpdate((Object) student);
	}

	public void update(Student student) {
		update((Object) student);
	}

	public void delete(java.io.Serializable id) {
		delete((Object) load(id));
	}

	public void delete(Student student) {
		delete((Object) student);
	}

}
