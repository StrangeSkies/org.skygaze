package uk.co.elionline.gears.utilities;

public interface Copyable<S extends Copyable<S>> {
	public/*Mutable*/S copy();
}
