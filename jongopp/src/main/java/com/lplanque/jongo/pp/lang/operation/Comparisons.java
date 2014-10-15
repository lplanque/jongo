package com.lplanque.jongo.pp.lang.operation;

import static com.lplanque.jongo.pp.lang.Operators.EQ;
import static com.lplanque.jongo.pp.lang.Operators.GT;
import static com.lplanque.jongo.pp.lang.Operators.GTE;
import static com.lplanque.jongo.pp.lang.Operators.IN;
import static com.lplanque.jongo.pp.lang.Operators.LT;
import static com.lplanque.jongo.pp.lang.Operators.LTE;
import static com.lplanque.jongo.pp.lang.Operators.NE;
import static com.lplanque.jongo.pp.lang.Operators.NIN;


import static com.lplanque.jongo.pp.util.Assert.assertNotNull;
import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public final class Comparisons {
	
	// INNER TYPE
	// ----------
	
	public final static class Tuple {
		
		public final String field;
		public final Object value;
		
		private Tuple(String field, Object value) {
			this.field = field;
			this.value = value;
		}
		
		@Override public boolean equals(Object o) {
		    if(this == o) return true;
		    if(o == null || getClass() != o.getClass()) return false;
		    final Tuple that = (Tuple)o;
		    return Objects.equal(this.field, that.field) 
		    	&& Objects.equal(this.value, that.value); 
		}
		
		@Override public int hashCode() {
		    return Objects.hashCode(field, value);
		}
		
		@Override public String toString() {
		    return MoreObjects.toStringHelper(this)
		    	.add("field", field)
		    	.add("value", value)
		    	.toString();
		}
	}
	
	// INNER METHODS
	// -------------
	
	private static Comparison build(final String op, final String field, final Object value) {
		
		assertNotNull(field, value);
		return new Comparison() {
			
			@Override public String template() {
				return format("{%s:{%s:#}}", field, value);
			}
			
			@Override public List<Object> parameters() {
				return Collections.singletonList(value);
			}
			
			@Override public String operator() {
				return op;
			}
		};
	}
	
	private static Comparison build(final String op, final String field, final Object left, final Object... rights) {
		
		assertNotNull(field, left, rights);
		return new Comparison() {

			@Override public String template() {
				final StringBuilder builder = new StringBuilder(32);
				builder.append('{').append(field);
				builder.append(":{").append(op);
				builder.append(":[#");
				for(int i = 0; i < rights.length; i++) {
					builder.append(",#");
				}
				return builder.append("]}}").toString();
			}

			@Override public List<Object> parameters() {	
				final int length = rights == null? 1: 1 + rights.length;
				final ArrayList<Object> params = new ArrayList<>(length);
				params.add(left);
				for(int i = 1; i < length; i++) {
					params.add(rights[i - 1]);
				}
				return Collections.unmodifiableList(params);
			}
			
			@Override public String operator() {
				return op;
			}
		};
	}
	
	// COMPARISON QUERIES
	// ------------------
	
	public static Tuple tuple(final String field, final Object value) {
		return new Tuple(field, value);
	}
	
	public static Comparison eq(final Tuple first, final Tuple... rems) {

		assertNotNull(first, rems);
		return new Comparison() {
			
			@Override public String template() {
				final StringBuilder builder = new StringBuilder();
				builder.append('{').append(first.field).append(":#");
				for(Tuple rem: rems) {
					if(rem != null) {
						builder.append(',').append(rem.field).append(":#");
					}
				}
				return builder.append('}').toString();
			}
			
			@Override public List<Object> parameters() {
				final ArrayList<Object> params = new ArrayList<>(1 + rems.length);
				for(Tuple rem: rems) {
					if(rem != null) {
						params.add(rem.value);
					}
				}
				return Collections.unmodifiableList(params);
			}
			
			@Override public String operator() {
				return EQ;
			}
		};
		
	}
	
	public static Comparison eq(final String field, final Object value) { // TODO Create a Match type ?
		return eq(tuple(field, value));
	}
	
	public static Comparison gt(final String field, final Object value) {
		return build(GT, field, value);
	}
	
	public static Comparison gte(final String field, final Object value) {
		return build(GTE, field, value);
	}
	
	public static Comparison lt(final String field, final Object value) {
		return build(LT, field, value);
	}
	
	public static Comparison lte(final String field, final Object value) {
		return build(LTE, field, value);
	}
	
	public static Comparison ne(final String field, final Object value) {
		return build(NE, field, value);
	}
	
	public static Comparison in(final String field, final Object left, final Object rights) {
		return build(IN, field, left, rights);
	}
	
	public static Comparison nin(final String field, final Object left, final Object rights) {
		return build(NIN, field, left, rights);
	}
}
