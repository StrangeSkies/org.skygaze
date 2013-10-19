package uk.co.strangeskies.gears.entity.declaration;

public interface DeclarationNodeTypeBuilder<T> {
	public DeclarationNodeTypeBuilder<T> name(String name);

	public DeclarationNodeTypeBuilder<T> dataClass(Class<T> dataClass);

	public DeclarationNodeTypeBuilder<T> implement(
			DeclarationNodeDataFactory<T> dataFactory);
}
