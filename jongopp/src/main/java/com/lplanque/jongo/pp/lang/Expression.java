package com.lplanque.jongo.pp.lang;

import java.util.List;

public interface Expression {
	String template();
	List<Object> parameters();
}
