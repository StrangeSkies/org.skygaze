package uk.co.elionline.gears.rendering.impl;

import java.awt.Color;
import java.util.Arrays;

import uk.co.elionline.gears.mathematics.expressions.CompoundExpression;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.rendering.Colour;

public class ColourIml extends CompoundExpression<ColourIml> implements Colour {
	private final DoubleValue red;
	private final DoubleValue green;
	private final DoubleValue blue;

	private final DoubleValue alpha;

	public ColourIml() {
		red = new DoubleValue();
		green = new DoubleValue();
		blue = new DoubleValue();

		alpha = new DoubleValue();

		getDependencies().set(Arrays.asList(red, green, blue, alpha));
	}

	public ColourIml(int red, int green, int blue, int alpha) {
		this();

		this.red.setValue(red / 255d);
		this.green.setValue(green / 255d);
		this.blue.setValue(blue / 255d);

		this.alpha.setValue(alpha / 255d);
	}

	public ColourIml(double red, double green, double blue, double alpha) {
		this();

		this.red.setValue(red);
		this.green.setValue(green);
		this.blue.setValue(blue);

		this.alpha.setValue(alpha);
	}

	public ColourIml(int red, int green, int blue) {
		this(red / 255d, green / 255d, blue / 255d, 1);
	}

	public ColourIml(ColourIml colour) {
		this(colour.getRed().doubleValue(), colour.getGreen().doubleValue(), colour
				.getBlue().doubleValue(), colour.getAlpha().doubleValue());
	}

	public ColourIml(Color colour) {
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
	public final ColourIml get() {
		return getThis();
	}

	@Override
	public ColourIml getDecoupledValue() {
		return this;
	}

	@Override
	public ColourIml getThis() {
		return this;
	}

	@Override
	public ColourIml copy() {
		return new ColourIml(this);
	}

	@Override
	protected ColourIml evaluate() {
		return this;
	}

	@Override
	public ColourIml set(ColourIml to) {
		red.setValue(to.getRed());
		green.setValue(to.getGreen());
		blue.setValue(to.getBlue());
		alpha.setValue(to.getAlpha());

		return this;
	}

	@Override
	public ColourIml multiply(Value<?> value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml multiply(int value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml multiply(long value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml multiply(float value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml multiply(double value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml divide(Value<?> value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml divide(int value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml divide(long value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml divide(float value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml divide(double value) {
		red.multiply(value);
		green.multiply(value);
		blue.multiply(value);

		alpha.multiply(value);

		return this;
	}

	@Override
	public ColourIml add(ColourIml value) {
		red.add(value.getRed());
		green.add(value.getGreen());
		blue.add(value.getBlue());

		alpha.add(value.getAlpha());

		return this;
	}

	@Override
	public ColourIml subtract(ColourIml value) {
		red.subtract(value.getRed());
		green.subtract(value.getGreen());
		blue.subtract(value.getBlue());

		alpha.subtract(value.getAlpha());

		return this;
	}

	@Override
	public ColourIml multiply(ColourIml value) {
		red.multiply(value.getRed());
		green.multiply(value.getGreen());
		blue.multiply(value.getBlue());

		alpha.multiply(value.getAlpha());

		return this;
	}

	@Override
	public ColourIml getMultiplied(Value<?> value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getMultiplied(int value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getMultiplied(long value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getMultiplied(float value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getMultiplied(double value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getDivided(Value<?> value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getDivided(int value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getDivided(long value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getDivided(float value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getDivided(double value) {
		return copy().multiply(value);
	}

	@Override
	public ColourIml getAdded(ColourIml value) {
		return copy().add(value);
	}

	@Override
	public ColourIml getSubtracted(ColourIml value) {
		return copy().subtract(value);
	}

	@Override
	public ColourIml getMultiplied(ColourIml value) {
		return copy().multiply(value);
	}
}
