package uk.co.strangeskies.gears.rendering;

public interface SceneFactory<T, C extends Camera<T>> {
	Scene<T> createScene();

	Renderable<T> createRenderable();

	C createCamera();
}
