package uk.co.strangeskies.gears.rendering;

import java.awt.Color;

import uk.co.strangeskies.gears.mathematics.Addable;
import uk.co.strangeskies.gears.mathematics.Multipliable;
import uk.co.strangeskies.gears.mathematics.Scalable;
import uk.co.strangeskies.gears.mathematics.Subtractable;
import uk.co.strangeskies.gears.mathematics.expressions.Variable;
import uk.co.strangeskies.gears.mathematics.values.DoubleValue;
import uk.co.strangeskies.gears.rendering.impl.ColourIml;
import uk.co.strangeskies.gears.utilities.Property;

public interface Colour extends Variable<ColourIml>,
		Property<ColourIml, ColourIml>, Addable<ColourIml, ColourIml>,
		Subtractable<ColourIml, ColourIml>, Scalable<ColourIml>,
		Multipliable<ColourIml, ColourIml> {
	public DoubleValue getRed();

	public DoubleValue getGreen();

	public DoubleValue getBlue();

	public DoubleValue getAlpha();

	public Color getColor();
}
