package de.creativecouple.tests.matchers;

import java.util.Comparator;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

public class IsOrderedCollection {

	public static <E extends Comparable<? super E>> Matcher<Iterable<E>> ordered() {
		return ordered(Comparator.naturalOrder());
	}

	public static <E> Matcher<Iterable<E>> ordered(final Comparator<? super E> comparator) {
		return Matchers.everyItem(new TypeSafeMatcher<E>() {

			private boolean hasPredecessor = false;
			private E predecessor = null;

			@Override
			public void describeTo(Description description) {
				description.appendText("given in natural order");
			}

			@Override
			protected boolean matchesSafely(E item) {
				boolean match = !hasPredecessor || matchesOrdering(item);
				stepPredecessor(item);
				return match;
			}

			private boolean matchesOrdering(E item) {
				return comparator.compare(item, predecessor) >= 0;
			}

			private void stepPredecessor(E item) {
				predecessor = item;
				hasPredecessor = true;
			}
		});
	}

}
