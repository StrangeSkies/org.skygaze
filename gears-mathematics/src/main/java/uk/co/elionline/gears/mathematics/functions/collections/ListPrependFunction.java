package uk.co.elionline.gears.mathematics.functions.collections;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.gears.mathematics.functions.Function;

public class ListPrependFunction<E> implements Function<List<E>, E> {
	@Override
	public List<E> applyTo(E input) {
		List<E> list = new ArrayList<>();
		list.add(input);
		return list;
	}
}
