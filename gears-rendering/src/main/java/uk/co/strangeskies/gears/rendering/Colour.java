package uk.co.strangeskies.gears.rendering;

import java.awt.Color;

import uk.co.strangeskies.gears.mathematics.Addable;
import uk.co.strangeskies.gears.mathematics.Multipliable;
import uk.co.strangeskies.gears.mathematics.Scalable;
import uk.co.strangeskies.gears.mathematics.Subtractable;
import uk.co.strangeskies.gears.mathematics.expression.Variable;
import uk.co.strangeskies.gears.mathematics.values.DoubleValue;
import uk.co.strangeskies.gears.utilities.Property;

public interface Colour extends Variable<Colour>, Property<Colour, Colour>,
		Addable<Colour, Colour>, Subtractable<Colour, Colour>, Scalable<Colour>,
		Multipliable<Colour, Colour> {
	public DoubleValue getRed();

	public DoubleValue getGreen();

	public DoubleValue getBlue();

	public DoubleValue getAlpha();

	public Color getColor();
}
