package com.qingshu.common.plugin.log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qingshu.base.pojo.JSLog;
import com.qingshu.base.pojo.JSLogDefine;
import com.qingshu.base.pojo.JSLogDetail;
import com.qingshu.base.pojo.JSTable;
import com.qingshu.base.pojo.JSTableField;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.annotation.MyField;
import com.qingshu.common.annotation.Pojo;
import com.qingshu.common.aspectj.AspectJModel;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.SessionInfo;
import com.qingshu.common.util.AnnotationUtil;
import com.qingshu.common.util.ContextUtils;
import com.qingshu.common.util.DataBaseUtils;
import com.qingshu.common.util.DateUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.ReflectHelper;
import com.qingshu.core.jdbc.dao.AbstractDao;
import com.qingshu.core.jdbc.dao.OracleDao;
import com.qingshu.core.mybatis.service.MbsCommonService;

/**
 * 系统操作日志辅助类
 */
public class LogUtils {
	public AspectJModel aspectJModel;
	public String methodName;
	public Object newObj;/* 修改后对象 */
	public Object oldObj;/* 修改前对象 */
	public List<Object> deleteObj;/* 删除对象集合 */
	public String entityName;/* 实体类名称 */
	public String entityPk;/* 修改前对象的主键值 */
	public String entityFk;/* 关联外键对象值 */
	public String entityFkName;/* 关联外键实体名称 */
	public Boolean insertLog = true;
	public List<JSTableField> logFields;
	public JSLogDefine jsLogDefine;
	public JSTable jsTable;
	public LogAnnotation logAnnotation;

	public LogUtils() {
	}

	public LogUtils(AspectJModel aspectJModel, LogAnnotation log) {
		this.aspectJModel = aspectJModel;
		this.logAnnotation = log;
		init();
	}

	private void init() {
		this.mbsCommonService = ContextUtils.getBean("mbsCommonService");
		methodName = aspectJModel.getMethodName();
		Object[] args = aspectJModel.getArgs();
		this.entityName = args[0].toString().substring(0, args[0].toString().indexOf("."));
		this.jsTable = mbsCommonService.selectOne("JSTable.findByEntity", entityName);
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("pojoName", entityName);
		map.put("methodName", methodName);
		this.jsLogDefine = mbsCommonService.selectOne("JSLogDefine.findByPojoName", map);
		if (!ObjectUtils.isEmpty(jsTable)) {
			this.logFields = mbsCommonService.selectList("JSTableField.findByTableName", this.entityName);
			if (methodName.equals(MethodEnum.update.toString())) {
				this.newObj = aspectJModel.getArgs()[1];
				this.entityPk = AnnotationUtil.getEntityPK(newObj);
				this.entityFk = AnnotationUtil.getEntityFK(newObj);
				this.entityFkName = AnnotationUtil.getEntityFKName(newObj);
				this.oldObj = mbsCommonService.selectOne(this.entityName + "." + "findById", entityPk);
			}
			if (methodName.equals(MethodEnum.insert.toString())) {
				this.newObj = aspectJModel.getArgs()[1];
				this.entityFk = AnnotationUtil.getEntityFK(newObj);
				this.entityFkName = AnnotationUtil.getEntityFKName(newObj);
			}
			if (methodName.equals(MethodEnum.batchInsert.toString())) {
				this.newObj = aspectJModel.getArgs()[1];
				this.entityFk = AnnotationUtil.getEntityFK(newObj);
				this.entityFkName = AnnotationUtil.getEntityFKName(newObj);
			}
			if (methodName.equals(MethodEnum.delete.toString())) {
				String[] ids = (String[]) aspectJModel.getArgs()[1];
				if (!ObjectUtils.isEmpty(ids)) {
					for (String id : ids) {
						deleteObj = ObjectUtils.getArrayList();
						Object delObj = mbsCommonService.selectOne(jsLogDefine.getFindPojoName() + "." + jsLogDefine.getFindMethod(), id);
						deleteObj.add(delObj);
					}
				}
			}
		} else {
			insertLog = false;
		}
	}

	/**
	 * 添加日志
	 * 
	 * @return
	 */
	public AjaxJson insertLog() {
		AjaxJson j = new AjaxJson();
		if (insertLog) {
			JSLog jsLog = getJsLog();
			String logId = "";
			if (!ObjectUtils.isEmpty(jsLog)) {
				if (methodName.equals(MethodEnum.insert.toString())) {
					mbsCommonService.insertLog(jsLog);
				}
				if (methodName.equals(MethodEnum.delete.toString())) {
					mbsCommonService.insertLog(jsLog);
				}
				if (methodName.equals(MethodEnum.update.toString())) {
					if (ObjectUtils.isEmpty(jsLog.getId())) {
						j=mbsCommonService.insertLog(jsLog);
						logId = j.getAttributes().get("id").toString();
					} else {
						logId = jsLog.getId();
					}
					List<JSLogDetail> logDetails = getLogDetail(logId);
					if (!ObjectUtils.isEmpty(logDetails)) {
						//mbsCommonService.insertLog(jsLog);
						mbsCommonService.batchInsert("JSLogDetail.batchInsert", logDetails);
					}
				}
			}
		}
		return j;

	}

	/**
	 * 获取日志对象
	 * 
	 * @return
	 */
	private JSLog getJsLog() {
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		Map<String, Object> logMap = ObjectUtils.getHashMap();
		logMap.put("entityFk", this.entityFk);
		logMap.put("entityFkName", this.entityFkName);
		logMap.put("userId", jUser.getId());
		JSLog jsLog = mbsCommonService.selectOne("JSLog.findByPF", logMap);
		if (jsLogDefine != null) {
			if (ObjectUtils.isEmpty(jsLog)) {
				jsLog = new JSLog();
			}
			if (ObjectUtils.isEmpty(entityFk)) {
				jsLog.setEntityName(entityName);
				jsLog.setEntityPk(entityPk);
			} else {
				jsLog.setEntityName(entityFkName);
				jsLog.setEntityPk(entityFk);
			}
			jsLog.setLogIp(ObjectUtils.getIpAddr());
			jsLog.setLogDate(DateUtils.getTimestamp());
			jsLog.setLogDesc(getLogDesc(jsLogDefine.getLogDesc()));
			jsLog.setJsUser(jUser);
			jsLog.setJsTable(jsTable);
			jsLog.setMethodName(methodName);
			jsLog.setUserName(jUser.getNickName());
		}
		return jsLog;
	}

	/**
	 * 日志记录实体字段修改记录
	 */
	public List<JSLogDetail> getLogDetail(String logId) {
		List<JSLogDetail> logDetails = ObjectUtils.getArrayList();
		if (!ObjectUtils.isEmpty(logFields)) {
			for (JSTableField jsTableField : logFields) {
				JSLogDetail logDetail = new JSLogDetail();
				ReflectHelper newHelper = new ReflectHelper(this.newObj);
				ReflectHelper oldHelper = new ReflectHelper(this.oldObj);
				Object newValue = newHelper.getMethodValue(jsTableField.getPojoName());
				Object oldValue = oldHelper.getMethodValue(jsTableField.getPojoName());
				logDetail.setFieldName(jsTableField.getFieldName());
				logDetail.setFieldDesc(jsTableField.getFieldDesc());
				String newvalue = ObjectUtils.getString(newValue, "");
				String oldvalue = ObjectUtils.getString(oldValue, "");
				if (!newvalue.equals(oldvalue)) {
					logDetail.setNewValue(ObjectUtils.getString(newValue, ""));
					logDetail.setOldValue(ObjectUtils.getString(oldValue, ""));
					logDetail.getJsLog().setId(logId);
					logDetails.add(logDetail);
				}
			}
		}
		return logDetails;
	}

	public static LogField getJsTableFields(Class<?> pojo) {
		LogField logField = new LogField();
		Pojo p = pojo.getAnnotation(Pojo.class);
		ReflectHelper reflectHelper = new ReflectHelper(pojo);
		Field[] fields = pojo.getDeclaredFields();
		List<JSTableField> jsTableFields = ObjectUtils.getArrayList();
		PreparedStatement pst = null;
		try {
			pst = DataBaseUtils.getConnection().prepareStatement("select * from " + p.name() + " where 1=1 ");
			ResultSetMetaData rsd = pst.executeQuery().getMetaData();
			for (int i = 0; i < rsd.getColumnCount(); i++) {
				JSTableField tableField = new JSTableField();
				tableField.setFieldLength(rsd.getColumnDisplaySize(i + 1));
				tableField.setFieldType(rsd.getColumnTypeName(i + 1));
				for (Field field : fields) {
					if (field.getName().toString().equalsIgnoreCase(rsd.getColumnName(i + 1))) {
						Method m = reflectHelper.getGetMethods().get(field.getName().toLowerCase());
						if (m.isAnnotationPresent(MyField.class)) {
							MyField myField = m.getAnnotation(MyField.class);
							tableField.setPojoName(myField.name());
							if (myField.islog()) {
								tableField.setIsLogField(1); // true
							} else {
								tableField.setIsLogField(0); //false
							}
							if (myField.indesc()) {
								logField.setLogField(myField.name());
							} else {
								if (ObjectUtils.isEmpty(logField.getLogField())) {
									logField.setLogField("id");
								}
							}
						} else {
							if (ObjectUtils.isEmpty(logField.getLogField())) {
								logField.setLogField("id");
							}
							tableField.setPojoName(field.getName().toString());
							tableField.setIsLogField(0);
						}
						tableField.setFieldName(rsd.getColumnName(i + 1));
						tableField.setTableName(pojo.getSimpleName());
						//mysql数据库new MysqlDao()
//						AbstractDao dao = new MysqlDao();
						//oracle数据库new OracleDao()
						AbstractDao dao = new OracleDao();
						tableField.setFieldDesc(dao.getFieldDesc(p.name(), field.getName()));
						jsTableFields.add(tableField);
					}
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataBaseUtils.closeConnection();
		}
		logField.setJsTableFields(jsTableFields);
		return logField;

	}

	public static List<JSTableField> getJsTableFields(String tableName) {
		List<JSTableField> jsTableFields = ObjectUtils.getArrayList();
		PreparedStatement pst = null;
		try {
			pst = DataBaseUtils.getConnection().prepareStatement("select * from " + tableName + " where 1=1");
			ResultSetMetaData rsd = pst.executeQuery().getMetaData();
			for (int i = 0; i < rsd.getColumnCount(); i++) {
				JSTableField tableField = new JSTableField();
				tableField.setFieldName(rsd.getColumnName(i + 1));
				tableField.setFieldLength(rsd.getColumnDisplaySize(i + 1));
				tableField.setFieldType(rsd.getColumnTypeName(i + 1));
				jsTableFields.add(tableField);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataBaseUtils.closeConnection();
		}
		return jsTableFields;

	}

	/**
	 * 组装日志描述
	 */
	private String getLogDesc(String logDesc) {
		if (logDesc.indexOf("#") != -1) {

			String logField = logDesc.substring(logDesc.indexOf("#") + 1, logDesc.lastIndexOf("#"));
			Object value = "";
			if (methodName.equals(MethodEnum.insert.toString())) {
				ReflectHelper reflectHelper = new ReflectHelper(this.newObj);
				value = reflectHelper.getMethodValue(logField);
			}
			if (methodName.equals(MethodEnum.update.toString())) {
				ReflectHelper reflectHelper = new ReflectHelper(this.oldObj);
				value = reflectHelper.getMethodValue(logField);
			}
			if (methodName.equals(MethodEnum.delete.toString())) {
				if (!ObjectUtils.isEmpty(deleteObj)) {
					String v = "";
					for (Object object : deleteObj) {
						if (!ObjectUtils.isEmpty(object)) {
							ReflectHelper reflectHelper = new ReflectHelper(object);
							Object tempObject=reflectHelper.getMethodValue(logField);
							if(tempObject==null){
								tempObject="";
							}
							v += tempObject.toString() + " ";
						}
					}
					value = v;
				}
			}

			logDesc = logDesc.replace("#" + logField + "#", ObjectUtils.getString(value,""));
		}
		return logDesc;

	}

	/**
	 * 比对新添加的数据和原始数据拼接成字符串
	 */
	public String getNewComparyOlds(Object newObject, Object oldObject) {
		StringBuffer sb = new StringBuffer();
		if (!ObjectUtils.isEmpty(logFields)) {
		}
		return sb.toString();
	}

	@Autowired
	public MbsCommonService mbsCommonService;
}
