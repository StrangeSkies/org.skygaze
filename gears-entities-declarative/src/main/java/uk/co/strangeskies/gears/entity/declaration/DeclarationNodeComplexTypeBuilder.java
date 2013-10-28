package uk.co.strangeskies.gears.entity.declaration;

import uk.co.strangeskies.gears.utilities.Factory;

public interface DeclarationNodeComplexTypeBuilder<T> extends
		Factory<DeclarationNodeComplexType<T>> {
	public DeclarationNodeComplexTypeBuilder<T> name(String name);

	public <U extends T> DeclarationNodeComplexTypeBuilder<U> dataClass(
			Class<U> dataClass);

	public DeclarationNodeComplexTypeBuilder<T> methodCall(String method,
			DeclarationNodeComplexType<?>... parameters);
}
