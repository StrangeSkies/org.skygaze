package uk.co.strangeskies.gears.mathematics.functions;

/**
 * Describes a function from F to T. A function should be stateless, that is to
 * say it should always produce the same result from the same input.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 *          To
 * @param <F>
 *          From
 */
public interface Function<T, F> {
  public T applyTo(F input);
}
