package uk.co.elionline.emerge.rendering;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.geometry.matrices.MatrixH2;
import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector2;

public class SpriteRenderable extends Renderable {
	private final Expression<? extends Sprite> sprite;
	private final Expression<? extends Vector2<?>> offset;

	public SpriteRenderable(Expression<? extends Sprite> sprite,
			Expression<? extends Vector2<?>> offset,
			Expression<? extends MatrixH2<?>> transformation) {
		super(transformation);

		this.sprite = sprite;
		this.offset = offset;
	}

	public Sprite getSprite() {
		return sprite.getValue();
	}

	public Vector2<?> getOffset() {
		return offset.getValue();
	}

	@Override
	public void renderTo(Renderer2D renderer) {
		super.renderTo(renderer);

		renderer.render(getSprite());
	}
}
