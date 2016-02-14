package de.creativecouple.tests.matchers;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.nullsFirst;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.util.Arrays;
import java.util.Comparator;

import org.hamcrest.Matcher;
import org.junit.Test;

public class IsOrderedCollectionTest {

	private static class Dummy {

		private final int order;

		private Dummy(int order) {
			this.order = order;
		}

	}

	private static final Comparator<Dummy> DUMMY_COMPARATOR = nullsFirst(comparingInt(dummy -> dummy.order));

	@Test
	public void ordered_isMatcher() {
		Matcher<?> matcher = Matchers.isOrdered();
		assertThat(matcher, instanceOf(Matcher.class));
	}

	@Test
	public void ordered_with_nonIterable_fails() {
		Matcher<?> matcher = Matchers.isOrdered();
		assertThat(matcher.matches(true), equalTo(false));
	}

	@Test(expected = ClassCastException.class)
	public void ordered_with_nonComparable_throws_exception() {
		Matcher<?> matcher = Matchers.isOrdered();
		matcher.matches(Arrays.asList(new Dummy(1), new Dummy(2), new Dummy(2)));
	}

	public void ordered_with_ordered_comparables_succeeds() {
		Matcher<?> matcher = Matchers.isOrdered();
		assertThat(matcher.matches(Arrays.asList(1, 2, 2)), equalTo(true));
	}

	public void ordered_with_nonOrdered_comparables_fails() {
		Matcher<?> matcher = Matchers.isOrdered();
		assertThat(matcher.matches(Arrays.asList(1, 2, 1)), equalTo(false));
	}

	@Test
	public void orderedComparator_isMatcher() {
		Matcher<?> matcher = Matchers.isOrdered(DUMMY_COMPARATOR);
		assertThat(matcher, instanceOf(Matcher.class));
	}

	@Test
	public void orderedComparator_with_nonIterable_fails() {
		Matcher<?> matcher = Matchers.isOrdered(DUMMY_COMPARATOR);
		assertThat(matcher.matches(true), equalTo(false));
	}

	@Test(expected = ClassCastException.class)
	public void orderedComparator_with_nonGenericClass_throws_exception() {
		Matcher<?> matcher = Matchers.isOrdered(DUMMY_COMPARATOR);
		matcher.matches(Arrays.asList(1, 2, 2));
	}

	public void orderedComparator_with_ordered_items_succeeds() {
		Matcher<?> matcher = Matchers.isOrdered(DUMMY_COMPARATOR);
		assertThat(matcher.matches(Arrays.asList(new Dummy(1), new Dummy(2), new Dummy(2))), equalTo(true));
	}

	public void orderedComparator_with_nonOrdered_items_fails() {
		Matcher<?> matcher = Matchers.isOrdered(DUMMY_COMPARATOR);
		assertThat(matcher.matches(Arrays.asList(new Dummy(1), new Dummy(2), new Dummy(1))), equalTo(false));
	}

}
