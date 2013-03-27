package uk.co.elionline.gears.rendering;

import java.awt.Color;

import uk.co.elionline.gears.mathematics.Addable;
import uk.co.elionline.gears.mathematics.Multipliable;
import uk.co.elionline.gears.mathematics.Scalable;
import uk.co.elionline.gears.mathematics.Subtractable;
import uk.co.elionline.gears.mathematics.expressions.CompoundExpression;
import uk.co.elionline.gears.mathematics.expressions.Variable;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Property;

public class Colour extends CompoundExpression<Colour> implements
		Variable<Colour>, Property<Colour, Colour>, Addable<Colour, Colour>,
		Subtractable<Colour, Colour>, Scalable<Colour>,
		Multipliable<Colour, Colour> {
	private final DoubleValue red;
	private final DoubleValue green;
	private final DoubleValue blue;

	private final DoubleValue alpha;

	public Colour() {
		red = new DoubleValue();
		green = new DoubleValue();
		blue = new DoubleValue();

		alpha = new DoubleValue();

		getDependencies().set(red, green, blue, alpha);
	}

	public Colour(int red, int green, int blue, int alpha) {
		this();

		this.red.setValue(red / 255d);
		this.green.setValue(green / 255d);
		this.blue.setValue(blue / 255d);

		this.alpha.setValue(alpha / 255d);
	}

	public Colour(double red, double green, double blue, double alpha) {
		this();

		this.red.setValue(red);
		this.green.setValue(green);
		this.blue.setValue(blue);

		this.alpha.setValue(alpha);
	}

	public Colour(int red, int green, int blue) {
		this(red / 255d, green / 255d, blue / 255d, 1);
	}

	public Colour(Colour colour) {
		this(colour.getRed().doubleValue(), colour.getGreen().doubleValue(), colour
				.getBlue().doubleValue(), colour.getAlpha().doubleValue());
	}

	public Colour(Color colour) {
		this(colour.getRed(), colour.getGreen(), colour.getBlue(), colour
				.getAlpha());
	}

	public DoubleValue getRed() {
		return red;
	}

	public DoubleValue getGreen() {
		return green;
	}

	public DoubleValue getBlue() {
		return blue;
	}

	public DoubleValue getAlpha() {
		return alpha;
	}

	public Color getColor() {
		return new Color(red.floatValue(), green.floatValue(), blue.floatValue(),
				alpha.floatValue());
	}

	@Override
	public final Colour get() {
		return getThis();
	}

	@Override
	public Colour getDecoupledValue() {
		return this;
	}

	@Override
	public Colour getThis() {
		return this;
	}

	@Override
	public Colour copy() {
		return new Colour(this);
	}

	@Override
	protected Colour evaluate() {
		return this;
	}

	@Override
	public Colour set(Colour to) {
		red.setValue(to.getRed());
		green.setValue(to.getGreen());
		blue.setValue(to.getBlue());
		alpha.setValue(to.getAlpha());

		return this;
	}

	@Override
	public Colour multiply(Value<?> value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour multiply(int value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour multiply(long value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour multiply(float value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour multiply(double value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour divide(Value<?> value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour divide(int value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour divide(long value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour divide(float value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour divide(double value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public Colour add(Colour value) {
		red.add(value.getRed());
		green.add(value.getGreen());
		blue.add(value.getBlue());

		alpha.add(value.getAlpha());

		return this;
	}

	@Override
	public Colour subtract(Colour value) {
		red.subtract(value.getRed());
		green.subtract(value.getGreen());
		blue.subtract(value.getBlue());

		alpha.subtract(value.getAlpha());

		return this;
	}

	@Override
	public Colour multiply(Colour value) {
		red.multiply(value.getRed());
		green.multiply(value.getGreen());
		blue.multiply(value.getBlue());

		alpha.multiply(value.getAlpha());

		return this;
	}

	@Override
	public Colour getMultiplied(Value<?> value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getMultiplied(int value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getMultiplied(long value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getMultiplied(float value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getMultiplied(double value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getDivided(Value<?> value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getDivided(int value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getDivided(long value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getDivided(float value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getDivided(double value) {
		return copy().multiply(value);
	}

	@Override
	public Colour getAdded(Colour value) {
		return copy().add(value);
	}

	@Override
	public Colour getSubtracted(Colour value) {
		return copy().subtract(value);
	}

	@Override
	public Colour getMultiplied(Colour value) {
		return copy().multiply(value);
	}
}
