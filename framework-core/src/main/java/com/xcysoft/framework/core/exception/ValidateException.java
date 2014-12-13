package com.xcysoft.framework.core.exception;

/**
 *
 * @author huangxin
 *
 */
public class ValidateException extends BaseException {

	private static final long serialVersionUID = -2441906100131506515L;

	public ValidateException() {

	}

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidateException(Throwable cause) {
		super(cause);
	}
}
