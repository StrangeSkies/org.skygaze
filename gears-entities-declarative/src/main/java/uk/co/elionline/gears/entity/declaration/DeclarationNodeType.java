package uk.co.elionline.gears.entity.declaration;

import java.util.Map;

import uk.co.elionline.gears.mathematics.functions.BinaryManipulation;
import uk.co.elionline.gears.utilities.Factory;

public interface DeclarationNodeType<T> {
	public String getName();

	public String setName();

	public Class<T> getDataClass();

	public Factory<T> getDataFactory();

	public BinaryManipulation<T, Map<String, DeclarationNode<T>>> getInitialiser();
}
