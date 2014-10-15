package com.lplanque.jongo.pp.lang;

import java.util.List;

public interface Template {
	String template();
	List<Object> parameters();
}
