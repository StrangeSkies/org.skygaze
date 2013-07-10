package uk.co.elionline.gears.rendering;

import java.awt.Color;

import uk.co.elionline.gears.mathematics.Addable;
import uk.co.elionline.gears.mathematics.Multipliable;
import uk.co.elionline.gears.mathematics.Scalable;
import uk.co.elionline.gears.mathematics.Subtractable;
import uk.co.elionline.gears.mathematics.expressions.Variable;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.rendering.impl.ColourIml;
import uk.co.elionline.gears.utilities.Property;

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
