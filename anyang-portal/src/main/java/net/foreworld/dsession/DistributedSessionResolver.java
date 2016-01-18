package net.foreworld.dsession;

import java.util.logging.Logger;

import net.foreworld.dsession.impl.HttpSessionImpl;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class DistributedSessionResolver implements
		HandlerMethodArgumentResolver {
	private final Logger logger;

	public DistributedSessionResolver() {
		logger = Logger.getLogger(getClass().getName());
	}

	// private static HaHaSession instance;

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer arg1, NativeWebRequest arg2,
			WebDataBinderFactory arg3) throws Exception {

		// Object o = BeanUtils.instantiate(parameter.getParameterType());
		//
		// logger.info(o.toString());

		// HttpSession session1 = getSession();
		// session1.setAttribute("kao", "这么难获取");
		//
		// HaHaSession session = new HaHaSession();
		//
		//
		// session.setName("wo ri");

		HttpSession instance = new HttpSessionImpl();
		//
		logger.info("==--12312355661122");
		// User user = new User();
		// user.setUserName("admin");
		// user.setUserPass("123456");
		return instance;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(DistributedSession.class);
	}
}
