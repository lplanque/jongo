package com.lplanque.jongo.pp.lang.logical;

import static com.lplanque.jongo.pp.lang.Operators.AND;
import static com.lplanque.jongo.pp.lang.Operators.NOR;
import static com.lplanque.jongo.pp.lang.Operators.NOT;
import static com.lplanque.jongo.pp.lang.Operators.OR;

import static com.lplanque.jongo.pp.util.Assert.assertNotNull;
import static com.lplanque.jongo.pp.util.Assert.assertSupported;

import static java.lang.String.format;

import java.util.Collections;
import java.util.List;

import com.lplanque.jongo.pp.lang.NonEmpty;
import com.lplanque.jongo.pp.lang.operation.Operation;

public final class Logicals {

	// INNER METHOD
	// ------------
	
	private static Logical build(
		final String op, final String field, final NonEmpty first, final NonEmpty sec, final NonEmpty... rem) {
		
		return new Logical() {
			
			@Override public String template() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override public List<Object> parameters() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override public String operator() {
				return op;
			}
		};
	}
	
	// LOGICAL OPERATIONS
	// ------------------

	public static Logical not(final String field, final Operation op) { // TODO Check parameters !
		
		assertNotNull(field, op);
		assertSupported(op.operator());
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