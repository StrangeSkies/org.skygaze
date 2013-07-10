package uk.co.elionline.gears.rendering.rendering3d.impl;

import java.util.HashSet;
import java.util.Set;

import uk.co.elionline.gears.rendering.Renderable;
import uk.co.elionline.gears.rendering.Scene;
import uk.co.elionline.gears.rendering.rendering3d.Data3D;

public class Scene3DImpl implements Scene<Data3D> {
	private final Set<Renderable<Data3D>> renderables;

	public Scene3DImpl() {
		renderables = new HashSet<>();
	}

	@Override
	public Set<Renderable<Data3D>> getRenderables() {
		return renderables;
	}
}
