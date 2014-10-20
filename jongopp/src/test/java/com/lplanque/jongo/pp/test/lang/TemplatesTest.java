package com.lplanque.jongo.pp.test.lang;

import static com.lplanque.jongo.pp.lang.Templates.*;
import static java.lang.String.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.lplanque.jongo.pp.lang.Template;

public class TemplatesTest {
	
	private final Template first = template(0);

	private final Template[] remainder = new Template[] {
		template(1),
		template(2)
	};
	
	private final Object[] patterns = {
		first.toString(),
		remainder[0].toString(),
		remainder[1].toString(),
	};
	
	public final int[] arities = {
		first.arity(),
		remainder[0].arity(),
		remainder[1].arity()
	};
	
	private Template template(final int arity) {	
		return template(format("(%s)", arity), arity);
	}
	
	private Template template(final String pattern, final int arity) {
		return new Template() {
			
			@Override public String toString() {
				return pattern;
			}
			
			@Override public int arity() {
				return arity;
			}
		};		
	}
	
	private void assertSame(Template expected, Template actual) {
		assertEquals(expected.toString(), actual.toString());
		assertEquals(expected.arity(), actual.arity());
	}

	@Test public void sharpsTest(/* generation of #-sequence */) {
		
		final Template[] expected = new Template[10];
		
		expected[0] = empty();
		expected[1] = template("#", 1);
		
		for(int i = 2; i < expected.length; i++) {
			final Template pred = expected[i - 1];
			expected[i] = template(format("%s,#", pred), pred.arity() + 1);
		}
		
		for(int i = 0; i < expected.length; i++) {
			assertSame(expected[i], sharps(i));
		}
	}
	
	@Test public void seqTest(/* sequence of templates */) {
		assertSame(first, seq(first));
		final Template expected = template(
			format("%s,%s,%s", patterns), arities(first, remainder));
		assertSame(expected, seq(first, remainder));
	}
	
	@Test public void braceTest(/* {}-operation */) {
		assertSame(template("{}", 0), brace(empty()));
		assertSame(template("{(1)}", 1), brace(remainder[0]));
	}
	
	@Test public void arrayTest(/* []-operation */) {
		assertSame(template("[]", 0),array(empty()));
		assertSame(template("[(1)]", 1), array(remainder[0]));
	}
	
	@Test public void tupleTest(/* x,y -> "x:y" */) {
		assertSame(template("field:#", 1), tuple("field"));
		final Template expected = template("field:" + first.toString(), first.arity());
		assertSame(expected, tuple("field", first));
	}
}
