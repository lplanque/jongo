package com.lplanque.jongo.pp.lang.query;

import static com.lplanque.jongo.pp.lang.Templates.array;
import static com.lplanque.jongo.pp.lang.Templates.brace;
import static com.lplanque.jongo.pp.lang.Templates.sharps;
import static com.lplanque.jongo.pp.lang.Templates.tuple;

import static com.lplanque.jongo.pp.lang.query.Operators.GT;
import static com.lplanque.jongo.pp.lang.query.Operators.GTE;
import static com.lplanque.jongo.pp.lang.query.Operators.IN;
import static com.lplanque.jongo.pp.lang.query.Operators.LT;
import static com.lplanque.jongo.pp.lang.query.Operators.LTE;
import static com.lplanque.jongo.pp.lang.query.Operators.NE;
import static com.lplanque.jongo.pp.lang.query.Operators.NIN;
import static com.lplanque.jongo.pp.lang.query.Operators.NOT;

import com.lplanque.jongo.pp.lang.Container;
import com.lplanque.jongo.pp.lang.Template;

public final class Operation extends Container {
	
	private final String operator;
	
	protected Operation(final String operator, final Template template) {
		super(template);
		this.operator = operator;
	}

	public final String operator() {
		return operator;
	}
	
	// BUILDER METHODS
	// ---------------

	private static Operation nary(final String operator, final int n) {
		
		final int max = Math.max(0, n);
		return new Operation(operator, tuple(operator, array(sharps(max))));
	}
	
	private static Operation single(final String operator) {
		return new Operation(operator, tuple(operator));
	}
	
	public static Operation not(final Operation operation) {
		
		return new Operation(NOT, tuple(NOT, brace(operation)));
	}
	
	// COMPARISON OPERATIONS
	// ---------------------
	
	public static Operation gt() {
		return single(GT);
	}
	
	public static Operation gte() {
		return single(GTE);
	}
	
	public static Operation in() {
		return nary(IN, 1);
	}
	
	public static Operation in(final int n) {
		return nary(IN, n);
	}
	
	public static Operation lt() {
		return single(LT);
	}
	
	public static Operation lte() {
		return single(LTE);
	}
	
	public static Operation ne() {
		return single(NE);
	}
	
	public static Operation nin() {
		return nary(NIN, 1);
	}
	
	public static Operation nin(final int n) {
		return nary(NIN, n);
	}
}
