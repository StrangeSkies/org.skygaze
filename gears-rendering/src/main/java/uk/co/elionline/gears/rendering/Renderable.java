package uk.co.elionline.gears.rendering;

public interface Renderable<T> {
	public void setVisible(boolean visible);

	public boolean isVisible();

	public <X extends T> X putExtensionData(X extensionData);

	public <X extends T> X removeExtensionData(Class<X> extensionDataClass);

	public <X extends T> X getExtensionData(Class<X> extensionDataClass);
}
