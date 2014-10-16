package com.lplanque.jongo.pp.test.lang.query;

import static com.lplanque.jongo.pp.lang.query.Matchs.*;
import static java.lang.String.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.lplanque.jongo.pp.lang.query.Match;

public final class MatchsTest {
	
	private static final String FIELD = "field";
	private static final Object VALUE = "value";
	
	public static final Match EQ_MATCH = eq(FIELD, VALUE);

	@Test public void eqTest(/* equality matcher */) {
		assertEquals(FIELD, EQ_MATCH.field());
		assertEquals(format("%s:#", FIELD), EQ_MATCH.pattern());
		assertNotNull(EQ_MATCH.parameters());
		assertFalse(EQ_MATCH.parameters().isEmpty());
		assertEquals(VALUE, EQ_MATCH.parameters().get(0));
	}
	
	@Test public void withTest(/* TODO */) {
		
	}
}
