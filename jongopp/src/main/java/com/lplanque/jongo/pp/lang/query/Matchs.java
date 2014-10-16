package com.lplanque.jongo.pp.lang.query;

import static com.lplanque.jongo.pp.lang.query.Templates.buildParameters;
import static com.lplanque.jongo.pp.lang.query.Templates.seq;
import static com.lplanque.jongo.pp.lang.query.Templates.tuple;

import static java.lang.String.format;

import java.util.Collections;
import java.util.List;

public final class Matchs {

	// BUILDER METHODS
	// ---------------
	
	public static Match eq(final String field, final Object parameter) {
		
		return new _Match(field) {
			
			@Override public String pattern() {
				return format("%s:#", field);
			}
			
			@Override public List<Object> parameters() {
				return Collections.singletonList(parameter);
			}
		};
	}
	
	public static Match with(final String field, final Operation first, final Operation... remainder) {
		
		return new _Match(field) {
			
			@Override public String pattern() {
				return tuple(field,seq(first, remainder));
			}
			
			@Override public List<Object> parameters() {
				return buildParameters(first, remainder);
			}
		};
	}
	
	// INNER CLASS
	// -----------
	static abstract class _Match implements Match {
		
		final String field;
		
		_Match(final String field) {
			this.field = field;
		}
		
		@Override public final String field() {
			return field;
		}
	}
}
