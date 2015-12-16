package com.qingshu.common.directive;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qingshu.common.util.DirectiveUtil;
import com.qingshu.core.mybatis.service.MbsCommonService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 公共标签调用入口
 * 页面使用：<@fm fun="方法名" 参数1..参数2></@fm>
 */
@SuppressWarnings("rawtypes")
public class FreemarkerDirective implements TemplateDirectiveModel {
	@Autowired
	protected MbsCommonService mbsCommonService;
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		CommonDirective commonDirective = new CommonDirective(mbsCommonService);
		String funName = DirectiveUtil.getString("fun", params);
		try {
			Method method = commonDirective.getClass().getMethod(funName, new Class[] { Map.class });
			params = (Map) method.invoke(commonDirective, params);
		} catch (Exception e) {
		}
		Map<String, TemplateModel> origMap = DirectiveUtil.addParamsToVariable(env, params);
		body.render(env.getOut());
		DirectiveUtil.removeParamsFromVariable(env, params, origMap);
	}
}
