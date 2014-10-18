package com.lplanque.jongo.pp.test.lang.query;

import static com.lplanque.jongo.pp.lang.query.Operations.*;
import static com.lplanque.jongo.pp.lang.query.Operators.*;
import static org.junit.Assert.*;
import static java.lang.String.*;

import org.junit.Test;

import com.lplanque.jongo.pp.lang.query.Operation;

public class OperationsTest {
	
	// Comparisons operation
	public static final Operation GT_OPERATION  = gt();
	public static final Operation GTE_OPERATION = gte();
	public static final Operation IN_OPERATION  = in(2);
	public static final Operation LT_OPERATION  = lt();
	public static final Operation LTE_OPERATION = lte();
	public static final Operation NE_OPERATION  = ne();
	public static final Operation NIN_OPERATION = nin(2);
	
	private void assertSingleOperation(String operator, Operation operation) {
		assertEquals(operator, operation.operator());
		assertEquals(format("%s:#", operator), operation.toString());
		assertEquals(1, operation.arity());
	}
	
	private void assertArrayOperation(String operator, Operation operation) {
		assertEquals(operator, operation.operator());
		assertEquals(format("%s:[#,#]", operator), operation.toString());
		assertEquals(2, operation.arity());
	}
	
	private void assertNotWith(final Operation operation) {
		final Operation not = not(operation);
		assertEquals(NOT, not.operator());
		assertEquals(format("%s:{%s}", NOT, operation), not.toString());
		assertEquals(operation.arity(), not.arity());		
	}
	
	@Test public void checkComparisons(/* tests comparison operation */) {
		assertSingleOperation(GT, GT_OPERATION);
		assertSingleOperation(GTE, GTE_OPERATION);
		assertSingleOperation(LT, LT_OPERATION);
		assertSingleOperation(LTE, LTE_OPERATION);
		assertSingleOperation(NE, NE_OPERATION);
		assertArrayOperation(IN, IN_OPERATION);
		assertArrayOperation(NIN, NIN_OPERATION);
	}
	
	@Test public void notTest(/* 'not' operator */) {
		assertNotWith(GT_OPERATION);
		assertNotWith(IN_OPERATION);
	}
}
