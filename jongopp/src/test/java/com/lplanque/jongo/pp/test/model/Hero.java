package com.lplanque.jongo.pp.test.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Hero {
	
	public static enum Psycho {
		BAD, BORDERLINE, NICE
	}
	
	public String name;
	public int strength;
	public Psycho psycho;
	
	public Hero() {
		
	}
	
	public Hero(String name, int strength, Psycho psycho) {
		this.name = name;
		this.strength = strength;
		this.psycho = psycho;
	}
	
	@Override
	public boolean equals(Object o) {
	    if(this == o) return true;
	    if(o == null || getClass() != o.getClass()) return false;
	    final Hero that = (Hero)o;
	    return Objects.equal(this.name, that.name) 
	    	&& Objects.equal(this.strength, that.strength) 
	    	&& Objects.equal(this.psycho, that.psycho);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hashCode(name, strength, psycho);
	}
	
	@Override
	public String toString() {
	    return MoreObjects.toStringHelper(this)
	    	.add("name", name)
	    	.add("strength", strength)
	    	.add("psycho", psycho)
	    	.toString();
	}
}
