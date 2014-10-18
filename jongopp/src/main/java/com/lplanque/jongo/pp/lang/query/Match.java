package com.lplanque.jongo.pp.lang.query;

import com.lplanque.jongo.pp.lang.GenericTemplate;
import com.lplanque.jongo.pp.lang.Template;

public final class Match extends GenericTemplate {
	
	private final String field;
	
	private Match(final String field, Template template) {
		super(template);
		this.field = field;
	}
	
	// BUILDER METHODS
	// ---------------
	
	public static Match eq(final String field) {
		return new Match(field, tuple(field));
	}
	
	public static Match with(final String field, final Operation first, final Operation... remainder) {
		return new Match(field, tuple(field, seq(first, remainder)));
	}

	public final String field() {
		return field;
	}
}
