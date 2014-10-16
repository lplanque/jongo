package com.lplanque.jongo.pp.test.lang.query;

import static com.lplanque.jongo.pp.lang.query.Templates.*;
import static java.lang.String.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.lplanque.jongo.pp.lang.query.Template;

public class TemplatesTest {
	
	private final Template first = new Template() {
		
		@Override public String pattern() {
			return "(0)";
		}
		
		@Override public List<Object> parameters() {
			return Collections.emptyList();
		}
	};

	private final Template[] remainder = new Template[] {
		
		new Template() {
			
			@Override public String pattern() {
				return "(1)";
			}
				
			@Override public List<Object> parameters() {
				return Collections.singletonList((Object)1);
			}
		},
		
		new Template() {
			
			@Override public String pattern() {
				return "(2)";
			}
			
			@Override public List<Object> parameters() {
				return Arrays.asList((Object)2, 3);
			}
		}
	};
	
	private final Object[] patterns = {
		first.pattern(),
		remainder[0].pattern(),
		remainder[1].pattern()
	};

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
