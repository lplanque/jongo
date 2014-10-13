package com.lplanque.jongo.pp.lang.expression;

/**
 * TODO Explain !
 * @author <a href="https://github.com/lplanque" target="_blank">Laurent Planque</a>
 * @since 0.1
 */
public final class Operations {
	
	private Operations() {
		// Utilities
	}
	
	// BUILDER METHODS
	// ---------------
	
	public static Operation all(Object... parameters) {
		return new _Operation("$all", ifNull(parameters));
	}
	
	public static Operation exists(boolean ami) {
		return new _Operation("$exists", ami);
	}
	
	public static Operation gt(Object parameter) {
		return new _Operation("$gt", parameter);	
	}
	
	public static Operation gte(Object parameter) {
		return new _Operation("$gte", parameter);	
	}
	
	public static Operation in(Object... parameters) {
		return new _Operation("$in", ifNull(parameters));
	}
	
	public static Operation lt(Object parameter) {
		return new _Operation("$lt", parameter);	
	}
	
	public static Operation lte(Object parameter) {
		return new _Operation("$lte", parameter);
	}
	
	public static Operation mod(int div, int rem) {
		return new _Operation("$mod", div, rem);
	}
	
	public static Operation ne(Object parameter) {
		return new _Operation("$ne", parameter);
	}
	
	public static Operation nin(Object... parameters) {
		return new _Operation("$nin", ifNull(parameters));
	}
	
	public static Operation type(int type) {
		return new _Operation("$type", type);
	}
	
	// INNER METHODS
	// -------------
	
	private static Object[] ifNull(Object... parameters) {
		return parameters == null? new Object[0]: parameters;
	}
	
	// INNER IMPLEMENTATION
	// --------------------
	
	private final static class _Operation implements Operation {
		
		private String op;
		private Object[] parameters;
		
		// Built at first instanciation if needed
		private String template;
		
		private _Operation() { 
			// For inner use only
		}
		
		_Operation(String op, Object... parameters) {
			this.op = op;
			this.parameters = parameters;
			generate();
		}

		@Override public String op() {
			return op;
		}
		
		@Override public String template() {
			return template;
		}
		
		@Override public Object[] parameters() {
			return parameters;
		}
		
		@Override public Operation and(Operation other) {
			final _Operation res = new _Operation();
			res.template = template(other);
			res.parameters = parameters(other);
			return res;
		}
		
		/*
		 * Generate string template for this part of query.
		 */
		private void generate() {
			// Create the prefix with mongo operator
			final StringBuilder buffer = new StringBuilder()
				.append('{')
				.append(op)
				.append(':');
			// Builds sequence of '#' according to the parameters size
			if(parameters.length == 1) {
				buffer.append('#');
			} else {
				sharps(buffer);
			}
			this.template = buffer.append('}').toString();
		}
		
		/*
		 * Builds sequence of '#' according to the parameters size
		 */
		private void sharps(StringBuilder buffer) {
			buffer.append('[');
			for(int i = 0; i < parameters.length - 1; i++) {
				buffer.append("#,");
			}
			for(int i = parameters.length - 1; i < parameters.length; i++) {
				buffer.append('#');
			}
			buffer.append(']');
		}

		/*
		 * Get all params from left (this) to right.
		 */
		private Object[] parameters(Operation other) {
			// Get the left parameters
			final Object[] left = parameters;
			// Get the right parameters
			final Object[] right = other.parameters();
			// Container for all parameters
			final Object[] all = new Object[left.length + right.length];
			// Copy from left to all
			System.arraycopy(left, 0, all, 0, left.length);
			// Copy right after left parameters
			System.arraycopy(right, 0, all, left.length, right.length);
			return all;
		}

		/*
		 * Get composition of comparison operation templates. As a template
		 * for a query part is of the form {.*}, then the resulting template
		 * will be like {.*,.*}. 
		 */
		private String template(Operation other) {
			// Get the left part, minus ending '}', adding ',' separator
			final StringBuilder left = new StringBuilder(template())
				.deleteCharAt(template().length() - 1)
				.append(',');
			// Get the right part, minus the first '{'
			final StringBuilder right = new StringBuilder(other.template())
				.deleteCharAt(0);
			// Return left + right
			return new StringBuilder(left)
				.append(right)
				.toString();
		}
	}
}
