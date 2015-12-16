package com.qingshu.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.Id;

import com.qingshu.common.annotation.Fk;
import com.qingshu.common.plugin.log.LogAnnotation;

/**
 * 注解工具类
 */
public class AnnotationUtil {
	/**
	 * 
	 * 获取实体类主键值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getEntityPK(Object newObject) {
		T t = null;
		Field[] fields = newObject.getClass().getSuperclass().getDeclaredFields();
		ReflectHelper reflectHelper = new ReflectHelper(newObject);
		for (Field field : fields) {
			Method m = reflectHelper.getGetSuperMethods().get(field.getName().toLowerCase());
			if(m!=null){
				if (m.isAnnotationPresent(Id.class)) {
					t = (T) reflectHelper.getSuperMethodValue(field.getName().toString());
				}
			}
			if(t==null){
				t = (T) reflectHelper.getSuperMethodValue("id");
			}
		}
		return t;
	}

	/**
	 * 
	 * 获取实体类外键值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getEntityFK(Object newObject) {
		T t = null;
		Field[] fields = newObject.getClass().getDeclaredFields();
		ReflectHelper reflectHelper = new ReflectHelper(newObject);
		for (Field field : fields) {
			Method m = reflectHelper.getGetMethods().get(field.getName().toLowerCase());
			if (!ObjectUtils.isEmpty(m)) {
				if (m.isAnnotationPresent(Fk.class)) {
					if(field!=null&&field.getName()!=null){
						t = (T) reflectHelper.getMethodValue(field.getName().toString());
					}
				}
			}
		}
		return t;
	}

	/**
	 * 
	 * 获取实体类外键值
	 */
	public static String getEntityFKName(Object newObject) {
		String t = null;
		Field[] fields = newObject.getClass().getDeclaredFields();
		ReflectHelper reflectHelper = new ReflectHelper(newObject);
		for (Field field : fields) {
			Method m = reflectHelper.getGetMethods().get(field.getName().toLowerCase());
			if (!ObjectUtils.isEmpty(m)) {
			if (m.isAnnotationPresent(Fk.class)) {
				Fk fk = m.getAnnotation(Fk.class);
				t = fk.fkname();
			}
			}
		}
		return t;
	}

	/**
	 * 获取添加日志注解的方法
	 */
	public static List<Method> getAnnotationMethods(Class<?> c) {
		List<Method> methodList = ObjectUtils.getArrayList();
		Method[] methods = c.getMethods();
		if (!ObjectUtils.isEmpty(methods)) {
			for (Method method : methods) {
				if (method.isAnnotationPresent(LogAnnotation.class)) {
					methodList.add(method);
				}
			}
		}
		return methodList;
	}

	/**
	 * 获取添加日志注解的方法
	 */
	public static List<Method> getAnnotationMethods(List<Class<?>> classList) {
		List<Method> methodList = ObjectUtils.getArrayList();
		for (Class<?> c : classList) {
			methodList.addAll(getAnnotationMethods(c));
		}
		return methodList;
	}
}
