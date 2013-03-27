package uk.co.elionline.emerge.rendering;

import java.util.Set;
import java.util.TreeSet;

import uk.co.elionline.emerge.utilities.IdentityComparator;


public class Scene {
  private final Renderer2D renderer;
  private final Set<SceneNode<?>> renderables;

  public Scene(Renderer2D renderer) {
    this.renderer = renderer;

    renderables = new TreeSet<>(new IdentityComparator<>());
  }

  public Set<SceneNode<?>> getSceneNodes() {
    return renderables;
  }

  synchronized public void render(double delta) {
    // delta = 1; // test rendering without tweening
    for (SceneNode<?> renderable : renderables) {
      renderable.render(renderer, delta);
    }

    renderer.display();
  }

  synchronized public void buffer() {
    for (SceneNode<?> renderable : renderables) {
      renderable.buffer();
    }
  }
}
