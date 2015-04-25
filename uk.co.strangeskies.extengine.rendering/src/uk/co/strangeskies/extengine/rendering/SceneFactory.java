package uk.co.strangeskies.extengine.rendering;


public interface SceneFactory<T, C extends Camera<T>> {
	Scene<T> createScene();

	Renderable<T> createRenderable();

	C createCamera();
}
