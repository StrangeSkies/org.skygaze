package uk.co.elionline.gears.input;

import uk.co.elionline.gears.input.MouseMovementAdapter;
import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.expressions.ImmutableExpression;
import uk.co.elionline.gears.mathematics.geometry.Translate;
import uk.co.elionline.gears.mathematics.geometry.matrices.MatrixH2;
import uk.co.elionline.gears.mathematics.geometry.matrices.Vector2;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.rendering.Renderable;
import uk.co.elionline.gears.rendering.SceneContext;
import uk.co.elionline.gears.rendering.SceneNode;
import uk.co.elionline.gears.rendering.Sprite;
import uk.co.elionline.gears.rendering.SpriteRenderable;

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
						"uk/co/elionline/gears/input/resources/cursor.png")),
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
