package com.lplanque.jongo.pp.util;

import static java.lang.String.format;

import com.lplanque.jongo.pp.lang.Operators;

public final class Assert {

	public static void assertSupported(String op) {
		if(!Operators.isMember(op)) {
			throw new RuntimeException(format("%s-operation is not supported", op));
		}
	}
	
	public static void assertSupported(String op, String... nots) {
		assertSupported(op);
		for(String not: nots) {
			if(op.equals(not)) { // 'op' should not be null here...
				throw new RuntimeException(format("%s-operation is not supported", op));
			}
		}
	}
	
	public static void assertNotNull(Object... params) {
		final RuntimeException re = new RuntimeException("parameter can not be null");
		if(params == null) throw re;
		for(Object param: params) {
			if(param == null) {
				throw re;
			}
		}
	}
}
