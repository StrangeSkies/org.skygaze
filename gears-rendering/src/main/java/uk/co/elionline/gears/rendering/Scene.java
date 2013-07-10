package uk.co.elionline.gears.rendering;

import java.util.Set;

public interface Scene<T> {
	public Set<Renderable<T>> getRenderables();
}
