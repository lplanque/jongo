package com.lplanque.jongo.pp.lang.query;

import java.util.List;

public interface Template {
	String pattern();
	List<Object> parameters();
}
