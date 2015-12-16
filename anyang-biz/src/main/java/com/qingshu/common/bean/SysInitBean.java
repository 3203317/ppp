package com.qingshu.common.bean;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.qingshu.base.pojo.JSLogDefine;
import com.qingshu.base.pojo.JSTable;
import com.qingshu.base.pojo.JSTableField;
import com.qingshu.common.annotation.Pojo;
import com.qingshu.common.plugin.log.LogAnnotation;
import com.qingshu.common.plugin.log.LogField;
import com.qingshu.common.plugin.log.LogUtils;
import com.qingshu.common.util.AnnotationUtil;
import com.qingshu.common.util.ClassUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.core.mybatis.dao.MbsCommonDao;
import com.qingshu.core.mybatis.service.MbsCommonService;

@Component
public class SysInitBean implements InitializingBean,ServletContextAware  {
	public void afterPropertiesSet() throws Exception {
		Set<Class<?>> allPojo = ClassUtils.getClasses("com.qingshu.base.pojo");
		List<JSTable> jsTables = ObjectUtils.getArrayList();
		List<Class<?>> mbs = ClassUtils.getAllClassByInterface(MbsCommonService.class);
		if (!ObjectUtils.isEmpty(allPojo)) {
			mbsCommonService.deleteNopar("JSLogDefine.deleteAll");
			mbsCommonService.deleteNopar("JSTable.deleteAll");
			mbsCommonService.deleteNopar("JSTableField.deleteAll");
			for (Iterator<Class<?>> iterator = allPojo.iterator(); iterator.hasNext();) {
				Class<?> pojo = (Class<?>) iterator.next();
				if (pojo.isAnnotationPresent(Pojo.class)) {
					Pojo p = pojo.getAnnotation(Pojo.class);
					JSTable jsTable = new JSTable();
					jsTable.setTableDesc(p.desc());
					jsTable.setTableName(p.name());
					jsTable.setEntityName(pojo.getSimpleName());
					jsTable.setPojoName(pojo.getName());
					jsTables.add(jsTable);
					LogField logField = LogUtils.getJsTableFields(pojo);
					List<JSTableField> tableFields=logField.getJsTableFields();
					mbsCommonDao.batchInsert("JSTableField.batchInsert", tableFields);
					List<Method> methods = AnnotationUtil.getAnnotationMethods(mbs);
					if (!ObjectUtils.isEmpty(methods)) {
						List<JSLogDefine> logDefines = ObjectUtils.getArrayList();
						for (Method method : methods) {
							LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
							JSLogDefine jsLogDefine = new JSLogDefine();
							jsLogDefine.setMethodName(method.getName());
							if(ObjectUtils.isEmpty(p.findPojoName()))
							{
								jsLogDefine.setFindPojoName(pojo.getSimpleName());
							}
							else {
								jsLogDefine.setFindPojoName(p.findPojoName());
							}
							jsLogDefine.setFindMethod(p.findMethod());
							jsLogDefine.setPojoName(pojo.getSimpleName());
							jsLogDefine.setTableDesc(p.desc());
							jsLogDefine.setLogDesc(logAnnotation.logDesc() + p.desc()+"--#"+logField.getLogField()+"#");
							logDefines.add(jsLogDefine);
						}
						if (!ObjectUtils.isEmpty(logDefines)) {
							mbsCommonDao.batchInsert("JSLogDefine.batchInsert", logDefines);
						}
					}
				}
			}
			if (!ObjectUtils.isEmpty(jsTables)) {
				mbsCommonDao.batchInsert("JSTable.batchInsert", jsTables);
				System.out.println("基础数据初始化:数据初始化成功...");
			}
		}
	}

	@Autowired
	public MbsCommonService mbsCommonService;
	@Autowired
	public MbsCommonDao mbsCommonDao;
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
}