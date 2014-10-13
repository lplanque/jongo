package com.lplanque.jongo.pp.test.delegate;

import static org.junit.Assert.*;

import com.lplanque.jongo.pp.delegate.JongoAction;
import com.lplanque.jongo.pp.delegate.ProxyDelegateMongoCollection;
import com.lplanque.jongo.pp.test.CommonTest;
import com.lplanque.jongo.pp.test.model.House;

import com.mongodb.WriteResult;

public class ProxyDelegateMongoCollectionTest extends CommonTest {
	
	private static final JongoAction<Object> PRE_INSERT = new JongoAction<Object>() {
		@Override public void on(Object entity) {
			final House house = (House)entity;
			assertEquals("INSERT", house.info);
			house.info = "PRE_INSERT";
		}
	};
	
	private static final JongoAction<Object> PRE_SAVE = new JongoAction<Object>() {
		@Override public void on(Object entity) {
			final House house = (House)entity;
			assertEquals("SAVE", house.info);
			house.info = "PRE_SAVE";			
		}
	};
	
	private static final JongoAction<Object> POST_INSERT = new JongoAction<Object>() {
		@Override public void on(Object entity) {
			final House house = (House)entity;
			assertEquals("PRE_INSERT", house.info);
			house.info = "POST_INSERT";			
		}
	};
	
	private static final JongoAction<Object> POST_SAVE = new JongoAction<Object>() {
		@Override public void on(Object entity) {
			final House house = (House)entity;
			assertEquals("PRE_SAVE", house.info);
			house.info = "POST_SAVE";					
		}
	};
	
	private ProxyDelegateMongoCollection proxy;
	private final int times;
	
	public ProxyDelegateMongoCollectionTest() {
		super();
		times = 10; // Number of try to check PRE < POST
	}
	
	private void create(/* check proxy after instanciation */) {
		proxy = new ProxyDelegateMongoCollection(docs);
		// Add JsonAction for pre and post operation on save and insert methods 
		proxy.preInsert(PRE_INSERT);
		proxy.preSave(PRE_SAVE);
		proxy.postInsert(POST_INSERT);
		proxy.postSave(POST_SAVE);
		// Check getters
		assertEquals(docs, proxy.getSource());
		assertEquals(PRE_INSERT, proxy.getPreInsert());
		assertEquals(PRE_SAVE, proxy.getPreSave());
		assertEquals(POST_INSERT, proxy.getPostInsert());
		assertEquals(POST_SAVE, proxy.getPostSave());
	}
	
	private void insert(/* check insert */) {
		for(int i = 0; i < times; i++) {
			final House house = marvel();
			house.info = "INSERT";
			final WriteResult res = proxy.insert(house);
			assertNotNull(res);
			assertEquals(1, res.getN());
			assertEquals("POST_INSERT", house.info);
		}
	}
	
	private void save(/* check save */) { // TODO factorize !
		for(int i = 0; i < times; i++) {
			House house = marvel();
			house.info = "SAVE";
			final WriteResult res = proxy.save(house);
			assertNotNull(res);
			assertEquals(1, res.getN());
			assertEquals("POST_SAVE", house.info);
		}
	}
	
	// TESTS
	// -----
	
	@Override
	public void go(/* SEQUENCE OF TESTS */) {
		create(/* check proxy after instanciation */);
		insert(/* check insert */);
		save(/* check save */);
	}
}
