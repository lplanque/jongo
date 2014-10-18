package com.lplanque.jongo.pp.test.lang.query;

import static com.lplanque.jongo.pp.lang.query.Match.*;
import static java.lang.String.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.lplanque.jongo.pp.lang.query.Match;

public final class MatchTest {
	
	private static final String FIELD = "field";
	
	public static final Match EQ_MATCH = eq(FIELD);

	@Test public void eqTest(/* equality matcher */) {
		assertEquals(FIELD, EQ_MATCH.field());
		assertEquals(format("%s:#", FIELD), EQ_MATCH.toString());
		assertEquals(1, EQ_MATCH.arity());
	}
	
	@Test public void withTest(/* TODO */) {
		
	}
}
