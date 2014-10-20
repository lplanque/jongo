package com.lplanque.jongo.pp.lang;

import static java.lang.String.format;

public final class Templates {
	
	public static final Template EMPTY = template("", 0);
	
	// BUILDER METHODS
	// ---------------
	
	private static Template template(final String pattern, final int arity) {
		
		return new Template() {
			
			@Override public String toString() {
				return pattern;
			}
			
			@Override public int arity() {
				return arity;
			}
		};
	}
	
	public static Template empty() {
		return EMPTY;
	}
	
	public static Template emptyIfNull(final Template template) {
		return template == null? EMPTY: template;
	}
	
	public static Template array(Template template) {
		final Template effective = emptyIfNull(template);
		return template(format("[%s]", effective), effective.arity());
	}
	
	public static Template brace(final Template template) {
		final Template effective = emptyIfNull(template);
		return template(format("{%s}", effective), effective.arity());
	}
	
	public static Template sharps(final int n) {
		if(n <= 0) {
			return EMPTY;
		} else {
			final StringBuilder builder = new StringBuilder();
			builder.append('#');
			for(int i = 1; i < n; i++) {
				builder.append(',').append('#');
			}
			return template(builder.toString(), n);
		}
	}
	
	public static Template seq(final Template first, final Template... rem) {
		if(first == null || rem == null) {
			return EMPTY;
		} else {
			final StringBuilder builder = new StringBuilder();
			builder.append(first); 
			if(rem != null) {
				for(Object o: rem) {
					builder.append(',').append(o);
				}
			}
			return template(builder.toString(), arities(first, rem));
		}
	}
	
	public static Template tuple(final Object left) {
		return template(format("%s:#", left), 1);
	}
	
	public static Template tuple(final Object left, final Template right) {
		final Template effective = emptyIfNull(right);
		return template(format("%s:%s", left, effective), effective.arity());
	}
	
	// ARITY BUILDER
	// -------------
	
	public static int arities(final Template first, final Template... remainder) {
		int n = first == null? 0: first.arity();
		if(remainder != null) {
			for(int i = 0; i < remainder.length; i++) {
				n+= remainder[i] == null? 0: remainder[i].arity();
			}
		}
		return n;
	}
}
