package com.lplanque.jongo.pp.lang.query;

import static com.lplanque.jongo.pp.lang.query.Operators.GT;
import static com.lplanque.jongo.pp.lang.query.Operators.GTE;
import static com.lplanque.jongo.pp.lang.query.Operators.IN;
import static com.lplanque.jongo.pp.lang.query.Operators.LT;
import static com.lplanque.jongo.pp.lang.query.Operators.LTE;
import static com.lplanque.jongo.pp.lang.query.Operators.NE;
import static com.lplanque.jongo.pp.lang.query.Operators.NIN;
import static com.lplanque.jongo.pp.lang.query.Operators.NOT;

import static com.lplanque.jongo.pp.lang.query.Templates.array;
import static com.lplanque.jongo.pp.lang.query.Templates.brace;
import static com.lplanque.jongo.pp.lang.query.Templates.sharps;
import static com.lplanque.jongo.pp.lang.query.Templates.tuple;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Operations {
	
	// BUILDER METHODS
	// ---------------

	private static Operation build(final String operator, final Object value) {
		
		return new _Operation(operator) {
			
			@Override public String pattern() {
				return tuple(operator);
			}
			
			@Override public List<Object> parameters() {
				return Collections.singletonList(value);
			}
		};
	}
	
	private static Operation build(final String operator, final Object... values) {
		
		final int length = values == null? 0: values.length;
		return new _Operation(operator) {
			
			@Override public String pattern() {
				return tuple(operator, array(sharps(length)));
			}
			
			@Override public List<Object> parameters() {
				return Collections.unmodifiableList(Arrays.asList(values));
			}
		};
	}
	
	public static Operation not(final Operation operation) {
		
		return new _Operation(NOT) {
			
			@Override public String pattern() {
				return tuple(op, brace(operation.pattern()));
			}
			
			@Override public List<Object> parameters() {
				return operation.parameters();
			}
		};
	}
	
	// COMPARISON OPERATIONS
	// ---------------------
	
	public static Operation gt(final Object value) {
		return build(GT, value);
	}
	
	public static Operation gte(final Object value) {
		return build(GTE, value);
	}
	
	public static Operation in(final Object... values) {
		return build(IN, values);
	}
	
	public static Operation lt(final Object value) {
		return build(LT, value);
	}
	
	public static Operation lte(final Object value) {
		return build(LTE, value);
	}
	
	public static Operation ne(final Object value) {
		return build(NE, value);
	}
	
	public static Operation nin(final Object... values) {
		return build(NIN, values);
	}
	
	// INNER CLASS
	// -----------
	
	static abstract class _Operation implements Operation {
		
		final String op;
		
		_Operation(final String op) {
			this.op = op;
		}
		
		@Override public final String operator() {
			return op;
		}
	}
}
