package uk.co.strangeskies.gears.entity.declaration;

import uk.co.strangeskies.gears.utilities.Factory;

public interface DeclarationNodeType<T> extends Factory<DeclarationNode<T>> {
	public String getName();

	public Class<T> getDataClass();
}
