package com.lplanque.jongo.pp.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public final class Operators {
	
	// PRIVATE CONTENT
	// ---------------

	/**
	 * Immutable set of declared operators.
	 */
	private static SortedSet<String> OPERATORS;
	
	private Operators() {
		// Constants
	}
	
	// Create the set of operators at the class loading
	static {
		final SortedSet<String> operators = new TreeSet<>();
		for(Field field: Operators.class.getFields()) {
			// By convention, if it is a string constant, then it is an operator
			if(isConstant(field) && String.class.equals(field.getType())) {
				try {
					operators.add((String)field.get(null));
				} catch (Throwable t) {
					// Can not happen ! ;-)
				}
			}
		}
		OPERATORS = Collections.unmodifiableSortedSet(operators);
	}
	
	/*
	 * Check is the given field is a constant.
	 */
	private static boolean isConstant(Field field) {
		final int mod = field.getModifiers();
		return Modifier.isPublic(mod)
			&& Modifier.isStatic(mod)
			&& Modifier.isFinal(mod);
	}

	// COMPARISON OPERATORS
	//---------------------
	
	public static final String GT  = "$gt";
	public static final String GTE = "$gte";
	public static final String IN  = "$in";
	public static final String LT  = "$lt";
	public static final String LTE = "$lte";
	public static final String NE  = "$ne";
	public static final String NIN = "$nin";
	
	// LOGICAL OPERATORS
	// -----------------
	
	public static final String AND = "$and";
	public static final String NOR = "$nor";
	public static final String NOT = "$not";
	public static final String OR  = "$or";
	
	// ELEMENT OPERATORS
	// -----------------
	
	public static final String EXISTS = "$exists";
	public static final String TYPE   = "$type";
	
	// EVALUATION OPERATORS
	// --------------------
	
	public static final String MOD   = "$mod";
	public static final String REGEX = "$regex";
	public static final String TEXT  = "$text";
	public static final String WHERE = "$where";
	
	// GEOSPATIAL OPERATORS
	// --------------------

	public static final String GEO_INTERSECTS = "$geoIntersects";
	public static final String GEO_WITHIN     = "$geoWithin";
	public static final String NEAR_SPHERE    = "$nearSphere";
	public static final String NEAR           = "$near";
	
	// ARRAY OPERATORS
	// ---------------
	
	public static final String ALL        = "$all";
	public static final String ELEM_MATCH = "$elemMatch";
	public static final String SIZE       = "$size";
	
	// OTHER...
	// --------
	
	/**
	 * Returns <code>true</code> if and only if the given parameter is a valid operator.
	 * @param maybe The <code>String</code> candidate to be a valid operator.
	 * @return <code>true</code> if and only if the given parameter is a valid operator.
	 */
	public static boolean isMember(final String maybe) {
		return OPERATORS.contains(maybe);
	}
	
	/**
	 * Get all operators into a <i>immutable</i> {@link SortedSet}. So, if you iterate on
	 * it, operators will be listed in the alphabetical order.
	 * @return All declared operators.
	 */
	public static SortedSet<String> all() {
		return OPERATORS;
	}
	
	/**
	 * Gives the number of declared operators.
	 * @return The number of declared operators.
	 */
	public static int howMany() {
		return OPERATORS.size();
	}
}
