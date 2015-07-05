package uk.co.strangeskies.extengine.entity.state;

import java.util.Collection;

import uk.co.strangeskies.utilities.factory.Factory;

/**
 * Implementations of this interface should always return a reference to the
 * object any method is invoked on (i.e. {@code return this}).
 *
 * @author Elias N Vasylenko
 *
 * @param <D>
 */
public interface StateComponentConfigurator<D extends C, C> extends
		Factory<StateComponent<D, C>> {
	/**
	 * The type bounding on T here isn't strictly necessary for normal use of this
	 * class, but it prevents heap corruption when old references are held to the
	 * object this method is called on. In other words, it should be safe for
	 * implementations to hide the warning produced by the cast required to return
	 * the invocation receiver.
	 *
	 * @param dataFactory
	 * @return
	 */
	public <T extends U, U> StateComponentConfigurator<T, U> data(
			Factory<? extends T> dataFactory);

	public StateComponentConfigurator<D, C> name(String name);

	public StateComponentConfigurator<D, C> description(String description);

	public StateComponentConfigurator<D, C> readDependencies(
			Collection<? extends StateComponent<?, ?>> readDependencies);

	public StateComponentConfigurator<D, C> readDependencies(
			StateComponent<?, ?>... readDependencies);

	public StateComponentConfigurator<D, C> writeDependencies(
			Collection<? extends StateComponent<?, ?>> writeDependencies);

	public StateComponentConfigurator<D, C> writeDependencies(
			StateComponent<?, ?>... writeDependencies);
}
