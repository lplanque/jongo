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
		remainder[1].toString()
	};
	
	private Template template(final int n) {	
		return new Template() {
			
			@Override public String toString() {
				return format("(%s)", n);
			}
			
			@Override public int arity() {
				return n;
			}
		};
	}

	@Test public void sharpsTest(/* generation of #-sequence */) {
		assertEquals("", sharps(0));
		assertEquals("#", sharps(1));
		assertEquals("#,#", sharps(2));
	}
	
	@Test public void seqTest(/* sequence of templates */) {
		assertEquals(patterns[0], seq(first));
		assertEquals(format("%s,%s,%s", patterns), seq(first, remainder));
	}
	
	@Test public void braceTest(/* {}-operation */) {
		assertEquals("{}", brace(""));
		assertEquals("{(0)}", brace(first));
	}
	
	@Test public void arrayTest(/* []-operation */) {
		assertEquals("[]", array(""));
		assertEquals("[(0)]", array(first));
	}
	
	@Test public void tupleTest(/* x,y -> "x:y" */) {
		assertEquals("field:#", tuple("field"));
		assertEquals("field:value", tuple("field", "value"));
		assertEquals("field:(0)", tuple("field", first));
	}
}
