package com.lplanque.jongo.pp.lang.query;

import com.lplanque.jongo.pp.lang.Template;

public final class Queries {
	
	// EMPTY QUERY
	// -----------
	
	/**
	 * The unique instance representing an empty query.
	 */
	public static final Query EMPTY = new Query() {
		
		@Override public String toString() {
			return "{}";
		}
		
		@Override public int arity() {
			return 0;
		}

		@Override public int order() {
			return 0;
		}
	};
	
	/**
	 * Return the empty query. Empty query selects all documents of a given 
	 * class. This method has the same behavior as getting {@link #EMPTY}.
	 * @return The empty query.
	 */
	public static Template empty() {
		return EMPTY;
	}
}
