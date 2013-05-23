package uk.co.elionline.gears.entity.declaration;

public interface DeclarationNode<T> {
	public String getID();

	public String setID();

	public DeclarationNodeType<T> getDeclarationNodeType();

	public T getData();
}
