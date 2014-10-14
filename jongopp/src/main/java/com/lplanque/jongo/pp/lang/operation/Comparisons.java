package com.lplanque.jongo.pp.lang.operation;

import static com.lplanque.jongo.pp.lang.Operators.GT;
import static com.lplanque.jongo.pp.lang.Operators.GTE;
import static com.lplanque.jongo.pp.lang.Operators.IN;
import static com.lplanque.jongo.pp.lang.Operators.LT;
import static com.lplanque.jongo.pp.lang.Operators.LTE;
import static com.lplanque.jongo.pp.lang.Operators.NE;
import static com.lplanque.jongo.pp.lang.Operators.NIN;
import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Comparisons {
	
	// COMPARISON QUERIES
	// ------------------
	
	private static Comparison build(final String op, final String field, final Object value) {
		return new Comparison() {
			
			@Override public String template() {
				return format("{%s:{%s:#}}", field, value);
			}
			
			@Override public List<Object> parameters() {
				return Collections.singletonList(value);
			}
			
			@Override public String operator() {
				return op;
			}
		};
	}
	
	private static Comparison build(final String op, final String field, final Object left, final Object... rights) {
		return new Comparison() {

			@Override public String template() {
				final StringBuilder builder = new StringBuilder("{%s:{%s:[#");		
				if(rights != null) {
					for(int i = 0; i < rights.length; i++) {
						builder.append(",#");
					}
				}
				return builder.append("]}}").toString();
			}

			@Override public List<Object> parameters() {
				final int length = rights == null? 1: 1 + rights.length;
				final ArrayList<Object> params = new ArrayList<>(length);
				params.add(left);
				for(int i = 1; i < length; i++) {
					params.add(rights[i - 1]);
				}
				return Collections.unmodifiableList(params);
			}
			
			@Override public String operator() {
				return op;
			}
		};
	}
	
	public static Comparison eq(final String field, final Object value) {
		return new Comparison() {
			
			@Override public String template() {
				return format("{%s:#}", field);
			}
			
			@Override public List<Object> parameters() {
				return Collections.singletonList(value);
			}
			
			@Override public String operator() {
				return null;
			}
		};
	}
	
	public static Comparison gt(final String field, final Object value) {
		return build(GT, field, value);
	}
	
	public static Comparison gte(final String field, final Object value) {
		return build(GTE, field, value);
	}
	
	public static Comparison lt(final String field, final Object value) {
		return build(LT, field, value);
	}
	
	public static Comparison lte(final String field, final Object value) {
		return build(LTE, field, value);
	}
	
	public static Comparison ne(final String field, final Object value) {
		return build(NE, field, value);
	}
	
	public static Comparison in(final String field, final Object left, final Object rights) {
		return build(IN, field, left, rights);
	}
	
	public static Comparison nin(final String field, final Object left, final Object rights) {
		return build(NIN, field, left, rights);
	}
}
