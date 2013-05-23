package uk.co.elionline.gears.entity.declaration;

public interface DeclarationNodeType<T> {
	public String getName();

	public String setName();

	public T getData();

	public Class<T> getNodeClass();
}
