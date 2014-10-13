package com.lplanque.jongo.pp.lang.logical;

import com.lplanque.jongo.pp.lang.Query;

public interface Logical extends Query {
	String op();
	Logical compose(Query... query);
	// TODO compact form !
}
