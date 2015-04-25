package uk.co.strangeskies.extengine.rendering.rendering3d.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.strangeskies.extengine.rendering.rendering3d.SceneFactory3D;

@Component
public class SceneFactory3DImpl implements SceneFactory3D {
	@Override
	public Scene3DImpl createScene() {
		return new Scene3DImpl();
	}

	@Override
	public Renderable3DImpl createRenderable() {
		return new Renderable3DImpl();
	}

	@Override
	public Camera3DImpl createCamera() {
		return new Camera3DImpl();
	}
}
