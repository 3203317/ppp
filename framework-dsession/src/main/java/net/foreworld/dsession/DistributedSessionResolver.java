package net.foreworld.dsession;

import java.util.logging.Logger;

import net.foreworld.dsession.impl.DistributedSessionImpl;
import net.foreworld.dsession.impl.HttpSessionImpl;
import net.foreworld.util.StringUtil;

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
	private static ThreadLocal<Object> session = new ThreadLocal<Object>();

	public DistributedSessionResolver() {
		logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer arg1, NativeWebRequest arg2,
			WebDataBinderFactory arg3) throws Exception {
		Object _o = session.get();
		if (null == _o) {
			if (null == StringUtil.isEmpty(DistributedSessionContext.DB_HOST)) {
				_o = new HttpSessionImpl();
			} else {
				_o = new DistributedSessionImpl();
				logger.info("distributed session");
			} // END
			session.set(_o);
		} // END
		return _o;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(DistributedSession.class);
	}
}
