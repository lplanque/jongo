package com.lplanque.jongo.pp.lang;

import static java.lang.String.format;

public final class Templates {
	
	public static final Template EMPTY = new Template() {
		
		@Override public String toString() {
			return "";
		}
		
		@Override public int arity() {
			return 0;
		}
	};
	
	// BUILDER METHODS
	// ---------------
	
	public static Template empty() {
		return EMPTY;
	}
	
	public static Template emptyIfNull(final Template template) {
		return template == null? EMPTY: template;
	}
	
	public static Template array(Template template) {
		
		final Template effective = emptyIfNull(template);
		return new Template() {
			
			@Override public String toString() {
				return format("[%s]", effective);
			}
			
			@Override public int arity() {
				return effective.arity();
			}
		};
	}
	
	public static Template brace(final Template template) {
		
		final Template effective = emptyIfNull(template);
		return new Template() {
			
			@Override public String toString() {
				return format("{%s}", effective);
			}
			
			@Override public int arity() {
				return effective.arity();
			}
		};
	}
	
	public static Template sharps(final int n) {
		
		return n <= 0? EMPTY: new Template() {
			
			@Override public String toString() {
				final StringBuilder builder = new StringBuilder();
				builder.append('#');
				for(int i = 1; i < n; i++) {
					builder.append(',').append('#');
				}
				return builder.toString();
			}
			
			@Override public int arity() {
				return n;
			}
		};
	}
	
	public static Template seq(final Template first, final Template... rem) {
		
		return first == null || rem == null? EMPTY: new Template() {
			
			@Override public String toString() {
				final StringBuilder builder = new StringBuilder();
				builder.append(first); 
				if(rem != null) {
					for(Object o: rem) {
						builder.append(',').append(o);
					}
				}
				return builder.toString();				
			}
			
			@Override public int arity() {
				return arities(first, rem);
			}
		};
	}
	
	public static Template tuple(final Object left) {

		return new Template() {
			
			@Override public String toString() {
				return format("%s:#", left);
			}
			
			@Override public int arity() {
				return 1;
			}
		};
	}
	
	public static Template tuple(final Object left, final Template right) {
		
		final Template effective = emptyIfNull(right);
		return new Template() {
			
			@Override public String toString() {
				return format("%s:%s", left, effective);
			}
			
			@Override public int arity() {
				return effective.arity();
			}
		};
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
