package uk.co.strangeskies.gears.mathematics.values.quantities;

import java.util.List;
import java.util.Map;

import uk.co.strangeskies.gears.mathematics.values.Fraction;
import uk.co.strangeskies.gears.utilities.function.collection.ListTransformOnceView;
import uk.co.strangeskies.gears.utilities.function.collection.UnmodifiableMapFunction;

public class QuantityType {
	private final String name;
	private final String symbol;

	private final List<Map<QuantityType, Fraction>> equivalencies;

	public QuantityType(String name, String symbol,
			List<Map<QuantityType, Fraction>> equivalencies) {
		this.name = name;
		this.symbol = symbol;

		this.equivalencies = equivalencies;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public List<Map<QuantityType, Fraction>> getEquivalencies() {
		return new ListTransformOnceView<>(equivalencies,
				new UnmodifiableMapFunction<QuantityType, Fraction>());
	}

	public Dimension getDimension() {
		return new Dimension.Builder().multiply(this).create();
	}
}
