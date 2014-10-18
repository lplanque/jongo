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

public final class Operations {
	
	// BUILDER METHODS
	// ---------------

	private static Operation nary(final String operator, final int n) {
		
		final int max = Math.max(0, n);
		return new _Operation(operator) {
			
			@Override public String toString() {
				return tuple(operator, array(sharps(max)));
			}
			
			@Override public int arity() {
				return max;
			}
		};
	}
	
	private static Operation single(final String operator) {
		return new _Operation(operator) {
			
			@Override public String toString() {
				return tuple(operator);
			}
			
			@Override public int arity() {
				return 1;
			}
		};
	}
	
	public static Operation not(final Operation operation) {
		
		return new _Operation(NOT) {
			
			@Override public String toString() {
				return tuple(op, brace(operation));
			}
			
			@Override public int arity() {
				return operation.arity();
			}
		};
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
