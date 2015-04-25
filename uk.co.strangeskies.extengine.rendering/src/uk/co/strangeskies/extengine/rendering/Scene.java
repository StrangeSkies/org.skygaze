package uk.co.strangeskies.extengine.rendering;

import java.util.Set;

public interface Scene<T> {
	public Set<Renderable<T>> getRenderables();
}
