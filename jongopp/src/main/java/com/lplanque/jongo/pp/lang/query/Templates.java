package com.lplanque.jongo.pp.lang.query;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Templates {
	
	// PATTERN BUILDERS
	// ----------------
	
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
	
	public static String seq(Template first, Template... rem) {
		final StringBuilder builder = new StringBuilder();
		builder.append(first.pattern()); 
		for(Template t: rem) {
			if(t != null) { 
				builder.append(',').append(t.pattern()); 
			}
		}
		return builder.toString();
	}
	
	public static String brace(String s) {
		return format("{%s}", s);
	}
	
	public static String brace(Template t) {
		return brace(t.pattern());
	}
	
	public static String array(String s) {
		return format("[%s]", s);
	}
	
	public static String array(Template t) {
		return array(t.pattern());
	}
	
	public static String tuple(String left) {
		return format("%s:#", left);
	}
	
	public static String tuple(String left, String right) {
		return format("%s:%s", left, right);
	}
	
	public static String tuple(String left, Template right) {
		return format("%s:%s", left, right.pattern());
	}
	
	// PARAMETERS BUILDER
	public static List<Object> buildParameters(final Template first, final Template... remainder) {
		final List<Object> all = new ArrayList<>();
		if(first != null) {
			all.addAll(first.parameters());
		}
		if(remainder != null) {
			for(Template t: remainder) {
				all.addAll(t.parameters());
			}
		}
		return Collections.unmodifiableList(all);
	}
}
