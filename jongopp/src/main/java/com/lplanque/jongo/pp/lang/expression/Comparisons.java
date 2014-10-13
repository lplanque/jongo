package com.lplanque.jongo.pp.lang.expression;

import static java.lang.String.format;

public final class Comparisons {

	private Comparisons() {
		// Utlities
	}
	
	// BUILDER METHODS
	public static Comparison eq(final String field, final Object value) {
		return new Comparison() {
			
			@Override public String template() {
				return format("{%s:#}", field);
			}
			
			@Override public Object[] parameters() {
				return new Object[] { value };
			}
			
			@Override public String field() {
				return field;
			}
		};
	}
	
	public static Comparison match(final String field, final Operation op) {
		return new _Comparison(field, op);
	}
	
	// INNER CLASS
	// -----------
	
	private final static class _Comparison implements Comparison {

		private final String field;
		private final Operation operation;
		
		_Comparison(String field, Operation operation) {
			this.field = field;
			this.operation = operation;
		}
		
		@Override public String field() {
			return field;
		}
		
		@Override public Object[] parameters() {
			return operation.parameters();
		}

		@Override public String template() {
			return format("{%s:%s}", field, operation.template());
		}
	}
}
 