package com.lplanque.jongo.pp.test.lang.query;

import static com.lplanque.jongo.pp.lang.query.Operators.*;
import static org.junit.Assert.*;

import java.util.SortedSet;

import org.junit.Test;

public class OperatorsTest {
	
	private final SortedSet<String> all = all();

	@Test public void checkOperators(/* checks operator consistency */) {
		
		// Build a non-operator
		final String NaO = notAnOperator();
		int i = 0;
		
		// Check operators
		assertMember(GT);             i++;
		assertMember(GTE);            i++;
		assertMember(IN);             i++;
		assertMember(LT);             i++;
		assertMember(LTE);            i++;
		assertMember(NE);             i++;
		assertMember(NIN);            i++;
		assertMember(AND);            i++;
		assertMember(NOR);            i++;
		assertMember(NOT);            i++;
		assertMember(OR);             i++;
		assertMember(EXISTS);         i++;
		assertMember(TYPE);           i++;
		assertMember(MOD);            i++;
		assertMember(REGEX);          i++;
		assertMember(TEXT);           i++;
		assertMember(WHERE);          i++;
		assertMember(GEO_INTERSECTS); i++;
		assertMember(GEO_WITHIN);     i++;
		assertMember(NEAR_SPHERE);    i++;
		assertMember(NEAR);           i++;
		assertMember(ALL);            i++;
		assertMember(ELEM_MATCH);     i++;
		assertMember(SIZE);           i++;
		
		// Check non-operators
		assertFalse(isMember(null));
		assertFalse(isMember(NaO));
		assertFalse(all.contains(NaO));
		
		// Check size
		assertEquals(i, all.size());
		assertEquals(i, howMany());
	}

	private void assertMember(String maybe) {
		assertTrue(isMember(maybe));
		assertTrue(all.contains(maybe));
	}
	
	private String notAnOperator() {
		final StringBuilder builder = new StringBuilder();
		for(String op: all) {
			builder.append(op);
		}
		return builder.toString();
	}
}
