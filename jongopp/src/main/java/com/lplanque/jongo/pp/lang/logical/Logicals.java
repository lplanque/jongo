package com.lplanque.jongo.pp.lang.logical;

import static java.lang.String.format;

import com.lplanque.jongo.pp.lang.Query;

/**
 * 
 * @author <a href="https://github.com/lplanque" target="_blank">Laurent Planque</a>
 * @since 0.1
 */
public final class Logicals {

	private static final class _Logical implements Logical {

		private String op;
		private Query[] queries;
		
		// Built after first instanciation if needed
		private String template;
		private Object[] parameters;
		
		private _Logical() { }
		
		_Logical(String op, Query... queries) {
			this.op = op;
			this.queries = queries;
			generate();
		}

		@Override
		public Object[] parameters() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String template() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String op() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Logical compose(Query... queries) {
			final _Logical res = new _Logical();
			res.template = format("{%s:%s}", op, template(queries));
			res.parameters = parameters(queries);
			return res;
		}
		
		private Object[] parameters(Query[] queries) {
			// TODO Auto-generated method stub
			return null;
		}

		private String template(Query[] queries) {
			// TODO Auto-generated method stub
			return null;
		}

		private void generate() {
			// Create the prefix with mongo operator
			final StringBuilder buffer = new StringBuilder()
				.append('{')
				.append(op)
				.append(':');
			// Build template and parameters
			if(queries.length == 1) {
				buffer.append(queries[0].template());
				parameters = queries[0].parameters();
			} else {
				sharps(buffer);
			}
			this.template = buffer.append('}').toString();
		}
		
		/*
		 * Builds sequence of '#' according to the parameters size
		 */
		private void sharps(StringBuilder buffer) {
			buffer.append('[');
			for(int i = 0; i < parameters.length - 1; i++) {
				buffer.append("#,");
			}
			for(int i = parameters.length - 1; i < parameters.length; i++) {
				buffer.append('#');
			}
			buffer.append(']');
		}
	}
}
