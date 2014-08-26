package uk.co.strangeskies.gears.rendering.impl;

import java.awt.Color;
import java.util.Arrays;

import uk.co.strangeskies.gears.rendering.Colour;
import uk.co.strangeskies.mathematics.expression.CompoundExpression;
import uk.co.strangeskies.mathematics.values.DoubleValue;
import uk.co.strangeskies.mathematics.values.Value;

public class ColourImpl extends CompoundExpression<Colour> implements Colour {
	private final DoubleValue red;
	private final DoubleValue green;
	private final DoubleValue blue;

	private final DoubleValue alpha;

	public ColourImpl() {
		red = new DoubleValue();
		green = new DoubleValue();
		blue = new DoubleValue();

		alpha = new DoubleValue();

		getDependencies().set(Arrays.asList(red, green, blue, alpha));
	}

	public ColourImpl(int red, int green, int blue, int alpha) {
		this();

		this.red.setValue(red / 255d);
		this.green.setValue(green / 255d);
		this.blue.setValue(blue / 255d);

		this.alpha.setValue(alpha / 255d);
	}

	public ColourImpl(double red, double green, double blue, double alpha) {
		this();

		this.red.setValue(red);
		this.green.setValue(green);
		this.blue.setValue(blue);

		this.alpha.setValue(alpha);
	}

	public ColourImpl(int red, int green, int blue) {
		this(red / 255d, green / 255d, blue / 255d, 1);
	}

	public ColourImpl(ColourImpl colour) {
		this(colour.getRed().doubleValue(), colour.getGreen().doubleValue(), colour
				.getBlue().doubleValue(), colour.getAlpha().doubleValue());
	}

	public ColourImpl(Color colour) {
		this(colour.getRed(), colour.getGreen(), colour.getBlue(), colour
				.getAlpha());
	}

	@Override
	public DoubleValue getRed() {
		return red;
	}

	@Override
	public DoubleValue getGreen() {
		return green;
	}

	@Override
	public DoubleValue getBlue() {
		return blue;
	}

	@Override
	public DoubleValue getAlpha() {
		return alpha;
	}

	@Override
	public Color getColor() {
		return new Color(red.floatValue(), green.floatValue(), blue.floatValue(),
				alpha.floatValue());
	}

	@Override
	public final Colour get() {
		return getThis();
	}

	@Override
	public ColourImpl copy() {
		return new ColourImpl(this);
	}

	@Override
	protected ColourImpl evaluate() {
		return this;
	}

	@Override
	public ColourImpl set(Colour to) {
		red.setValue(to.getRed());
		green.setValue(to.getGreen());
		blue.setValue(to.getBlue());
		alpha.setValue(to.getAlpha());

		return this;
	}

	@Override
	public ColourImpl multiply(Value<?> value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl multiply(int value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl multiply(long value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl multiply(float value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl multiply(double value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl divide(Value<?> value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl divide(int value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl divide(long value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl divide(float value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl divide(double value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourImpl add(Colour value) {
		red.add(value.getRed());
		green.add(value.getGreen());
		blue.add(value.getBlue());

		alpha.add(value.getAlpha());

		return this;
	}

	@Override
	public ColourImpl subtract(Colour value) {
		red.subtract(value.getRed());
		green.subtract(value.getGreen());
		blue.subtract(value.getBlue());

		alpha.subtract(value.getAlpha());

		return this;
	}

	@Override
	public ColourImpl multiply(Colour value) {
		red.multiply(value.getRed());
		green.multiply(value.getGreen());
		blue.multiply(value.getBlue());

		alpha.multiply(value.getAlpha());

		return this;
	}

	@Override
	public ColourImpl getMultiplied(Value<?> value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getMultiplied(int value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getMultiplied(long value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getMultiplied(float value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getMultiplied(double value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getDivided(Value<?> value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getDivided(int value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getDivided(long value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getDivided(float value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getDivided(double value) {
		return copy().multiply(value);
	}

	@Override
	public ColourImpl getAdded(Colour value) {
		return copy().add(value);
	}

	@Override
	public ColourImpl getSubtracted(Colour value) {
		return copy().subtract(value);
	}

	@Override
	public ColourImpl getMultiplied(Colour value) {
		return copy().multiply(value);
	}
}
