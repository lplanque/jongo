package com.lplanque.jongo.pp.test;

import static com.lplanque.jongo.pp.test.model.Hero.Psycho.BAD;
import static com.lplanque.jongo.pp.test.model.Hero.Psycho.BORDERLINE;
import static com.lplanque.jongo.pp.test.model.Hero.Psycho.NICE;

import java.util.ArrayList;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.Mapper;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.JacksonMapper;
import org.junit.Before;
import org.junit.Rule;

import com.fasterxml.jackson.datatype.jdk7.Jdk7Module;

import com.github.fakemongo.junit.FongoRule;

import com.lplanque.jongo.pp.test.model.Hero;
import com.lplanque.jongo.pp.test.model.House;

public abstract class CommonTest {

	protected static final List<House> HOUSES = new ArrayList<>();
	
	protected static final Mapper JACKSON_MAPPER = new JacksonMapper
		.Builder()
		.registerModule(new Jdk7Module())
		.build();
	
	static {
		HOUSES.add(marvel());
		HOUSES.add(dc());
		HOUSES.add(toei());
		HOUSES.add(other());
	}
	
	@Rule
	public FongoRule rule = new FongoRule();
	
	protected MongoCollection docs;
	
	public static House marvel() {
		final List<Hero> heroes = new ArrayList<>();
		heroes.add(new Hero("hulk", 100, BORDERLINE));
		heroes.add(new Hero("wolverine", 10, BORDERLINE));
		heroes.add(new Hero("thor", 90, NICE));
		heroes.add(new Hero("loki", 70, BAD));
		final House house = new House("marvel", heroes); 
		return house;
	}
	
	public static House dc() {
		final List<Hero> heroes = new ArrayList<>();
		heroes.add(new Hero("batman", 10, NICE));
		heroes.add(new Hero("superman", 100, NICE));
		heroes.add(new Hero("wonder woman", 80, NICE));
		heroes.add(new Hero("dark seid", 100, BAD));
		final House house = new House("dc", heroes);
		return house;
	}
	
	public static House toei() {
		final List<Hero> heroes = new ArrayList<>();
		heroes.add(new Hero("songoku", 100, NICE));
		heroes.add(new Hero("vegeta", 90, BORDERLINE));
		heroes.add(new Hero("cell", 50, BAD));
		heroes.add(new Hero("seiya", 20, NICE));
		final House house = new House("toei", heroes);
		return house;
	}

	public static House other() {
		final List<Hero> heroes = new ArrayList<>();
		heroes.add(new Hero("optimus prime", 30, NICE));
		heroes.add(new Hero("splinter", 10, NICE));
		heroes.add(new Hero("kick ass", 10, BORDERLINE));
		heroes.add(new Hero("red mist", 1, BAD));		
		final House house = new House("other", heroes);
		return house;
	}
	
	@Before 
	public void init(/* Create the Jongo instance */) {
		final Jongo jongo = new Jongo(rule.getDB(), JACKSON_MAPPER);
		docs = jongo.getCollection(getClass().getSimpleName());
		for(House house: HOUSES) {
			docs.save(house);
		}
	}
	
	public abstract void go();
}
