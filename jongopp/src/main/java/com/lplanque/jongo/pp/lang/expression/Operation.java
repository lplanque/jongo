package com.lplanque.jongo.pp.lang.expression;

import com.lplanque.jongo.pp.lang.Part;

public interface Operation extends Part { 
	String op();
	Operation and(Operation other);
}
