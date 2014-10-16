package com.lplanque.jongo.pp.test.lang.query;

import static com.lplanque.jongo.pp.lang.query.Operations.*;
import static com.lplanque.jongo.pp.lang.query.Operators.*;
import static org.junit.Assert.*;
import static java.lang.String.*;

import org.junit.Test;

import com.lplanque.jongo.pp.lang.query.Operation;

public class OperationsTest {
	
	// Comparisons operation
	public static final Operation GT_OPERATION = gt(1);
	public static final Operation GTE_OPERATION = gte(1);
	public static final Operation IN_OPERATION = in(1,2);
	public static final Operation LT_OPERATION = lt(1);
	public static final Operation LTE_OPERATION = lte(1);
	public static final Operation NE_OPERATION = ne(1);
	public static final Operation NIN_OPERATION = nin(1,2);
	
	private void assertSimpleOperation(String operator, Operation operation) {
		assertEquals(operator, operation.operator());
		assertEquals(format("%s:#", operator), operation.pattern());
		assertNotNull(operation.parameters());
		assertEquals(1, operation.parameters().size());
		assertEquals(1, operation.parameters().get(0));
	}
	
	private void assertArrayOperation(String operator, Operation operation) {
		assertEquals(operator, operation.operator());
		assertEquals(format("%s:[#,#]", operator), operation.pattern());
		assertNotNull(operation.parameters());
		assertEquals(2, operation.parameters().size());
		assertEquals(1, operation.parameters().get(0));
		assertEquals(2, operation.parameters().get(1));
	}
	
	@Test public void checkComparisons(/* tests comparison operation */) {
		assertSimpleOperation(GT, GT_OPERATION);
		assertSimpleOperation(GTE, GTE_OPERATION);
		assertSimpleOperation(LT, LT_OPERATION);
		assertSimpleOperation(LTE, LTE_OPERATION);
		assertSimpleOperation(NE, NE_OPERATION);
		assertArrayOperation(IN, IN_OPERATION);
		assertArrayOperation(NIN, NIN_OPERATION);
	}
	
	@Test public void notTest(/* 'not' operator */) {
		final Operation operation = not(GT_OPERATION);
		assertEquals(NOT, operation.operator());
		assertEquals(format("%s:{%s}", NOT, GT_OPERATION.pattern()), operation.pattern());
		assertNotNull(operation.parameters());
		assertEquals(GT_OPERATION.parameters().size(), operation.parameters().size());
		assertEquals(GT_OPERATION.parameters().get(0), operation.parameters().get(0));
	}
}
