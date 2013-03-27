package uk.co.elionline.emerge.utilities;

public interface Copyable<S extends Copyable<S>> {
	public/*Mutable*/S copy();
}
