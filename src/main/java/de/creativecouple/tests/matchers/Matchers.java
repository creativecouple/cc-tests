package de.creativecouple.tests.matchers;

import java.util.Comparator;

import org.hamcrest.Matcher;

public class Matchers {

	public static <E extends Comparable<? super E>> Matcher<Iterable<E>> isOrdered() {
		return IsOrderedCollection.<E> ordered();
	}

	public static <E> Matcher<Iterable<E>> isOrdered(Comparator<? super E> comparator) {
		return IsOrderedCollection.<E> ordered(comparator);
	}

}
