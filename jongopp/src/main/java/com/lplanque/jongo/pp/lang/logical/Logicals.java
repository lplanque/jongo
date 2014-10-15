package com.lplanque.jongo.pp.lang.logical;

import static com.lplanque.jongo.pp.lang.Operators.AND;
import static com.lplanque.jongo.pp.lang.Operators.NOR;
import static com.lplanque.jongo.pp.lang.Operators.NOT;
import static com.lplanque.jongo.pp.lang.Operators.OR;

import static com.lplanque.jongo.pp.util.Assert.assertNotNull;
import static com.lplanque.jongo.pp.util.Assert.assertSupported;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lplanque.jongo.pp.lang.NonEmpty;
import com.lplanque.jongo.pp.lang.Operators;
import com.lplanque.jongo.pp.lang.operation.Operation;

public final class Logicals {

	// INNER METHOD
	// ------------
	
	private static Logical build(
		final String op, final String field, final NonEmpty first, final NonEmpty sec, final NonEmpty... rems) {
		
		assertNotNull(field, first, sec, rems);
		return new Logical() {
			
			@Override public String template() {
				final StringBuilder builder = new StringBuilder(32);
				builder.append('{').append(field);
				builder.append(":{").append(op);
				builder.append(":[").append(first.template());
				builder.append(',').append(sec.template());
				for(int i = 0; i < rems.length; i++) {
					if(rems[i] != null) {
						builder.append(",").append(rems[i].template());
					}
				}
				return builder.append("]}}").toString();
			}
			
			@Override public List<Object> parameters() {
				final ArrayList<Object> params = new ArrayList<>();
				params.addAll(first.parameters());
				params.addAll(sec.parameters());
				for(NonEmpty rem: rems) {
					if(rem != null) {
						params.add(rem);
					}
				}
				return Collections.unmodifiableList(params);
			}

			@Override public String operator() {
				return op;
			}
		};
	}
	
	// LOGICAL OPERATIONS
	// ------------------

	public static Logical not(final String field, final Operation op) {
		
		assertNotNull(field, op);
		assertSupported(op.operator(), Operators.REGEX);
		return new Logical() {
			
			@Override public String template() {
				return format("{%s:{%s:%s}}", field, NOT, op.template());
			}
			
			@Override public List<Object> parameters() {
				return op == null
					? Collections.emptyList()
					: op.parameters();
			}

			@Override public String operator() {
				return NOT;
			}
		};
	}
	
	public static Logical and(final String field, final NonEmpty first, final NonEmpty sec, final NonEmpty... rem) {
		return build(AND, field, first, sec, rem);
	}
	
	public static Logical or(final String field, final NonEmpty first, final NonEmpty sec, final NonEmpty... rem) {
		return build(OR, field, first, sec, rem);
	}
	
	public static Logical nor(final String field, final NonEmpty first, final NonEmpty sec, final NonEmpty... rem) {
		return build(NOR, field, first, sec, rem);
	}
}
