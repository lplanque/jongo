package com.lplanque.jongo.pp.lang.logical;

import static com.lplanque.jongo.pp.lang.Operators.AND;
import static com.lplanque.jongo.pp.lang.Operators.NOR;
import static com.lplanque.jongo.pp.lang.Operators.NOT;
import static com.lplanque.jongo.pp.lang.Operators.OR;
import static java.lang.String.format;

import java.util.Collections;
import java.util.List;

import com.lplanque.jongo.pp.lang.operation.Operation;

public final class Logicals {

	public static Logical not(final String field, final Operation op) { // TODO Check parameters !
		return new Logical() {
			
			@Override public String template() {
				return format("{%s:{%s:%s}}", field, NOT, op.template());
			}
			
			@Override public List<Object> parameters() {
				return op == null
					? Collections.emptyList()
					: op.parameters();
			}
		};
	}
}
