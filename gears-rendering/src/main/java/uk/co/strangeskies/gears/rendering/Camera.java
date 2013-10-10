package uk.co.strangeskies.gears.rendering;

public interface Camera<T> {
	public Scene<T> getScene();

	public void setScene(Scene<T> scene);
}
