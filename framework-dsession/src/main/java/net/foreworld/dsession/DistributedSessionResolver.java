package net.foreworld.dsession;

import net.foreworld.dsession.impl.DistributedSessionImpl;
import net.foreworld.dsession.impl.HttpSessionImpl;
import net.foreworld.util.StringUtil;
import net.foreworld.util.redis.RedisProp;

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
	private static ThreadLocal<Object> session = new ThreadLocal<Object>();

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer arg1, NativeWebRequest arg2,
			WebDataBinderFactory arg3) throws Exception {
		Object _o = session.get();
		if (null == _o) {
			if (null == StringUtil.isEmpty(RedisProp.get("db.host"))) {
				_o = new HttpSessionImpl();
			} else {
				_o = new DistributedSessionImpl();
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
