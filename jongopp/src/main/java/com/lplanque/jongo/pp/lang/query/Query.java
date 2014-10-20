package com.lplanque.jongo.pp.lang.query;

import static com.lplanque.jongo.pp.lang.Templates.brace;

import com.lplanque.jongo.pp.lang.Container;
import com.lplanque.jongo.pp.lang.Template;
import com.lplanque.jongo.pp.lang.Templates;

public abstract class Query extends Container {
	
	// EMPTY QUERY
	// -----------
	
	/**
	 * The unique instance representing an empty query.
	 */
	public static final Query EMPTY = new Query(brace(Templates.empty())) {
		@Override public int order() {
			return 0;
		}
	};
	
	protected Query(Template template) {
		super(template);
	}

	public abstract int order();
	
	/**
	 * Return the empty query. Empty query selects all documents of a given 
	 * class. This method has the same behavior as getting {@link #EMPTY}.
	 * @return The empty query.
	 */
	public static Template empty() {
		return EMPTY;
	}
}
