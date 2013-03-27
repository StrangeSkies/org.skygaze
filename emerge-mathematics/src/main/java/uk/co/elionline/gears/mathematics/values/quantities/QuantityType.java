package uk.co.elionline.gears.mathematics.values.quantities;

import java.util.List;
import java.util.Map;

import uk.co.elionline.gears.mathematics.functions.collections.ListTransformOnceView;
import uk.co.elionline.gears.mathematics.functions.collections.UnmodifiableMapFunction;
import uk.co.elionline.gears.mathematics.values.Fraction;

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
