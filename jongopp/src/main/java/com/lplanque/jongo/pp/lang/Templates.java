package com.lplanque.jongo.pp.lang;

import static java.lang.String.format;

public final class Templates {
	
	// PATTERN BUILDERS
	// ----------------
	
	public static String seq(Template first, Template... rem) {
		final StringBuilder builder = new StringBuilder();
		builder.append(first); 
		if(rem != null) {
			for(Object o: rem) {
				builder.append(',').append(o);
			}
		}
		return builder.toString();
	}
	
	public static String sharps(int n) {
		final StringBuilder builder = new StringBuilder();
		if(n > 0) {
			builder.append('#');
			for(int i = 1; i < n; i++) {
				builder.append(',').append('#');
			}
		}
		return builder.toString();
	}
	
	public static String brace(Object o) {
		return format("{%s}", o);
	}
	
	public static String array(Object o) {
		return format("[%s]", o);
	}
	
	public static String tuple(Object left) {
		return format("%s:#", left);
	}
	
	public static String tuple(Object left, Object right) {
		return format("%s:%s", left, right);
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
