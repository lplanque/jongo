package com.lplanque.jongo.pp.util;

import static java.lang.String.format;

import com.lplanque.jongo.pp.lang.Operators;

public final class Assert {

	public static void assertSupported(String op) {
		if(!Operators.isMember(op) || Operators.REGEX.equals(op)) {
			throw new RuntimeException(format("%s-operation is not supported", op));
		}
	}
	
	public static void assertNotNull(Object... params) {
		for(Object param: params) {
			if(param == null) {
				throw new RuntimeException("parameter can not be null");
			}
		}
	}
}
