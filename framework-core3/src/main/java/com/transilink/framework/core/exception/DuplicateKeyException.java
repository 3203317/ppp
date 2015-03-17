package com.transilink.framework.core.exception;

public class DuplicateKeyException extends BaseException {
	public DuplicateKeyException(Object key) {
		super("Can not add a object with the duplicate key \"" + key + "\"!");
	}
}