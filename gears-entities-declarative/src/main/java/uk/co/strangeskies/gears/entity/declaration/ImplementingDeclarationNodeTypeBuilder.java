package uk.co.strangeskies.gears.entity.declaration;

public interface ImplementingDeclarationNodeTypeBuilder<T> {
	public ImplementingDeclarationNodeTypeBuilder<T> implementationScript(
			String script);

	public ImplementingDeclarationNodeTypeBuilder<T> methodImplementationScript(
			String method, String script);

	public ImplementingDeclarationNodeTypeBuilder<T> methodReturnValue(
			String method, Object value);
}
