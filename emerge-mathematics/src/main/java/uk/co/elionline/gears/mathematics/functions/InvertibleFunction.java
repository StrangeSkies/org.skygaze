package uk.co.elionline.gears.mathematics.functions;

/**
 * Describes a function from F to T. A function should be stateless, hence the
 * implementing of {@link Immutable}.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 *          To
 * @param <F>
 *          From
 */
public interface InvertibleFunction<T, F> extends Function<T, F> {
	public InvertibleFunction<F, T> getInverse();
}
