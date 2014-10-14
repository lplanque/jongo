package com.lplanque.jongo.pp.lang;

import java.util.Collections;
import java.util.List;

public final class Queries {
	
	// EMPTY QUERY
	// -----------
	
	/**
	 * The unique instance representing an empty query.
	 */
	public static final Query EMPTY = new Query() {
		
		@Override public String template() {
			return "{}";
		}
		
		@Override public List<Object> parameters() {
			return Collections.emptyList();
		}
	};
	
	/**
	 * Return the empty query. Empty query selects all documents of a given 
	 * class. This method has the same behavior as getting {@link #EMPTY}.
	 * @return The empty query.
	 */
	public static Query empty() {
		return EMPTY;
	}
}
