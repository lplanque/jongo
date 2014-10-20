package com.lplanque.jongo.pp.lang.query;

import static com.lplanque.jongo.pp.lang.Templates.seq;
import static com.lplanque.jongo.pp.lang.Templates.tuple;

import com.lplanque.jongo.pp.lang.Container;
import com.lplanque.jongo.pp.lang.Template;

public final class Match extends Container {
	
	private final String field;
	
	private Match(final String field, final Template template) {
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
