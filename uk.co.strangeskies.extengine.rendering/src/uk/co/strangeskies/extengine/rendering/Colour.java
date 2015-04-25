package uk.co.strangeskies.extengine.rendering;

import java.awt.Color;

import uk.co.strangeskies.mathematics.expression.Variable;
import uk.co.strangeskies.mathematics.operation.Addable;
import uk.co.strangeskies.mathematics.operation.Multipliable;
import uk.co.strangeskies.mathematics.operation.Scalable;
import uk.co.strangeskies.mathematics.operation.Subtractable;
import uk.co.strangeskies.mathematics.values.DoubleValue;
import uk.co.strangeskies.utilities.Property;

public interface Colour extends Variable<Colour>, Property<Colour, Colour>,
		Addable<Colour, Colour>, Subtractable<Colour, Colour>, Scalable<Colour>,
		Multipliable<Colour, Colour> {
	public DoubleValue getRed();

	public DoubleValue getGreen();

	public DoubleValue getBlue();

	public DoubleValue getAlpha();

	public Color getColor();
}
