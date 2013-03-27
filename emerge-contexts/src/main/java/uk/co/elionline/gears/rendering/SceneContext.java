package uk.co.elionline.gears.rendering;

import uk.co.elionline.gears.contexts.Context;
import uk.co.elionline.gears.rendering.Scene;


public abstract class SceneContext extends Context {
	private Scene scene;

	public SceneContext() {
	}

	@Override
	protected boolean start() {
		scene = createScene();

		return true;
	}

	protected Scene createScene() {
		return new Scene(getRenderer());
	}

	public Scene getScene() {
		return scene;
	}

	@Override
	protected void buffer() {
		scene.buffer();
	}

	@Override
	public void render(double delta) {
		scene.render(delta);
	}
}
