package uk.co.strangeskies.extengine.rendering.rendering2d.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.strangeskies.extengine.rendering.rendering2d.SceneFactory2D;

@Component
public class SceneFactory2DImpl implements SceneFactory2D {
	@Override
	public Scene2DImpl createScene() {
		return new Scene2DImpl();
	}

	@Override
	public Renderable2DImpl createRenderable() {
		return new Renderable2DImpl();
	}

	@Override
	public Camera2DImpl createCamera() {
		return new Camera2DImpl();
	}
}
