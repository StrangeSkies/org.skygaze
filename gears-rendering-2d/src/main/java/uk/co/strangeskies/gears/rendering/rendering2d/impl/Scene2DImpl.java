package uk.co.strangeskies.gears.rendering.rendering2d.impl;

import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.gears.rendering.Renderable;
import uk.co.strangeskies.gears.rendering.Scene;
import uk.co.strangeskies.gears.rendering.rendering2d.Data2D;

public class Scene2DImpl implements Scene<Data2D> {
	private final Set<Renderable<Data2D>> renderables;

	public Scene2DImpl() {
		renderables = new HashSet<>();
	}

	@Override
	public Set<Renderable<Data2D>> getRenderables() {
		return renderables;
	}
}
