package com.lplanque.jongo.pp.test.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class House {
	
	public ObjectId _id;
	public String name;
	public List<Hero> heroes;
	public String info;
	
	public House(String name, List<Hero> heroes) {
		this.name = name;
		this.heroes = heroes;
	}
	
	@Override
	public boolean equals(Object o) {
	    if(this == o) return true;
	    if(o == null || getClass() != o.getClass()) return false;
	    final House that = (House)o;
	    return Objects.equal(this._id, that._id)
	    	&& Objects.equal(this.name, that.name)
	    	&& Objects.equal(this.heroes, that.heroes)
	    	&& Objects.equal(this.info, that.info);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hashCode(_id, name, heroes, info);
	}
	
	@Override
	public String toString() {
	    return MoreObjects.toStringHelper(this)
	    	.add("id", _id)
	    	.add("name", name)
	    	.add("heroes", heroes)
	    	.add("info", info)
	    	.toString();
	}
}
