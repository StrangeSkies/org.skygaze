package uk.co.strangeskies.gears.entity.declaration;

import java.util.Map;

import uk.co.strangeskies.gears.mathematics.functions.BinaryManipulation;
import uk.co.strangeskies.gears.utilities.Factory;

public interface DeclarationNodeType<T> {
	public String getName();

	public String setName();

	public Class<T> getDataClass();

	public Factory<T> getDataFactory();

	public BinaryManipulation<T, Map<String, DeclarationNode<T>>> getInitialiser();
}
