package com.lplanque.jongo.pp.lang;

import static com.lplanque.jongo.pp.lang.Templates.emptyIfNull;

public class Container implements Template {
	
	protected final Template template;
	
	protected Container(Template template) {
		this.template = emptyIfNull(template);
	}
	
	// OVERRIDED METHODS
	// -----------------
	
	@Override public final String toString() {
		return template.toString();
	}
	
	@Override public final int arity() {
		return template.arity();
	}
}
