package uk.co.elionline.emerge.input;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.expressions.ImmutableExpression;
import uk.co.elionline.emerge.mathematics.geometry.Translate;
import uk.co.elionline.emerge.mathematics.geometry.matrices.MatrixH2;
import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector2;
import uk.co.elionline.emerge.mathematics.values.DoubleValue;
import uk.co.elionline.emerge.mathematics.values.IntValue;
import uk.co.elionline.emerge.rendering.Renderable;
import uk.co.elionline.emerge.rendering.SceneContext;
import uk.co.elionline.emerge.rendering.SceneNode;
import uk.co.elionline.emerge.rendering.Sprite;
import uk.co.elionline.emerge.rendering.SpriteRenderable;

public abstract class InterfaceContext extends SceneContext {
	private MouseMovementAdapter mouseAdapter;

	private SceneNode<Renderable> cursor;
	Expression<MatrixH2<DoubleValue>> trans;

	@Override
	protected boolean start() {
		if (!super.start()) {
			return false;
		}

		// add cursor
		mouseAdapter = new MouseMovementAdapter(getInputController(),
				getInputController());
		mouseAdapter.reset();

		cursor = new SceneNode<Renderable>();
		/*-
				Expression<? extends Vector2<?>> interpolatedPosition = cursor
						.bufferDecoupledInterpolation(
								getMouseAdapter().getPosition(),
								new LinearInterpolationOperation<Vector2<DoubleValue>, Vector2<DoubleValue>>());

				Expression<? extends MatrixH2<?>> transformation = new Translate<>(
						new MatrixH2<>(DoubleValueFactory.instance()), interpolatedPosition);
		*/
		trans = new Translate<>(new MatrixH2<>(DoubleValue.factory()),
				getMouseAdapter().getPosition());

		SpriteRenderable cursorSprite = new SpriteRenderable(
				new ImmutableExpression<>(new Sprite(
						"uk/co/elionline/emerge/input/resources/cursor.png")),
				new Vector2<>(IntValue.factory(), 2, 2), trans);

		cursor.setRenderable(cursorSprite);

		getScene().getSceneNodes().add(cursor);
		getRenderer().showSystemCursor(false);

		return true;
	}

	protected void prepareInterface() {
		getMouseAdapter().prepare();
	}

	protected void finaliseInterface() {
		getMouseAdapter().finalise();
	}

	protected MouseMovementAdapter getMouseAdapter() {
		return mouseAdapter;
	}
}
