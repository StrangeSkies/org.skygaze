package uk.co.strangeskies.gears.entity.declaration;

public interface DeclarationNode<T> {
	public String getID();

	public String setID();

	public DeclarationNodeType<T> getType();

	public T getData();
}
