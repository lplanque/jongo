package com.lplanque.jongo.pp.test.lang.query;

import static com.lplanque.jongo.pp.lang.query.Query.*;
import static org.junit.Assert.*;

import org.junit.Test;

public final class QueriesTest {

	@Test public void emptyTest(/* empty query correctness */) {
		assertEquals(EMPTY, empty());
		assertEquals("{}", EMPTY.toString());
		assertEquals(0, EMPTY.arity());
	}
}
